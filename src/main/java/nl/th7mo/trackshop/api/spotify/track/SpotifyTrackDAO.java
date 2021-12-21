// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.track;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistResponseSpecBuilder;
import nl.th7mo.trackshop.api.spotify.request.SpotifyTrackRequestBuilder;

import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.google.gson.Gson;
import reactor.core.publisher.Mono;

@Component
public final class SpotifyTrackDAO {

    private final int MAX_RESPONSE_SIZE = 100;
    private int offset = 0;

    public SpotifyTracks getAll(String id) {
        SpotifyTracks allTracks = get(id);

        while (allTracks.next != null) {
            offset += MAX_RESPONSE_SIZE;
            allTracks.concat(get(id));
        }

        return allTracks;
    }

    private SpotifyTracks get(String id) {
        RequestHeadersSpec<?> request = SpotifyTrackRequestBuilder.build(
            id, offset
        );
        String responseJson = receiveResponse(request).block();

        return mapToSpotifyTracks(responseJson);
    }

    private Mono<String> receiveResponse(RequestHeadersSpec<?> request) {
        ResponseSpec response = request.retrieve();
        return SpotifyPlaylistResponseSpecBuilder.buildConstraints(response)
                .bodyToMono(String.class);
    }

    private SpotifyTracks mapToSpotifyTracks(String responseJson) {
        return new Gson().fromJson(responseJson, SpotifyTracks.class);
    }
}
