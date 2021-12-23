// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import nl.th7mo.trackshop.api.spotify.track.SpotifyTrackDAO;

import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.google.gson.Gson;
import reactor.core.publisher.Mono;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class SpotifyPlaylistDAO {

    private final SpotifyTrackDAO trackDAO;

    public SpotifyPlaylist get(String id) {
        RequestHeadersSpec<?> request = SpotifyPlaylistRequestBuilder.build(id);
        String responseJson = receiveResponse(request).block();

        SpotifyPlaylist spotifyPlaylist = mapToPlaylist(responseJson);
        spotifyPlaylist.setTracks(trackDAO.getAll(id));

        return spotifyPlaylist;
    }

    private Mono<String> receiveResponse(RequestHeadersSpec<?> request) {
        ResponseSpec response = request.retrieve();
        return SpotifyPlaylistResponseSpecBuilder.buildConstraints(response)
            .bodyToMono(String.class);
    }

    private SpotifyPlaylist mapToPlaylist(String responseJson) {
        return new Gson().fromJson(responseJson, SpotifyPlaylist.class);
    }
}
