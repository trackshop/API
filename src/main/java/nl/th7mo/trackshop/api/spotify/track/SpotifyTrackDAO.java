// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.track;

import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistResponseSpecBuilder;
import nl.th7mo.trackshop.api.util.DotenvAdapter;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.google.gson.Gson;
import reactor.core.publisher.Mono;

@Component
public final class SpotifyTrackDAO {

    private WebClient httpClient;

    private final int MAX_RESPONSE_SIZE = 100;
    private int offset = 0;

    public SpotifyTracks getAll(String id) {
        SpotifyTracks allTracks = get(id);

        while (allTracks.next != null) {
            offset += MAX_RESPONSE_SIZE;
            SpotifyTracks partOfTracks = get(id);
            allTracks.next = partOfTracks.next;
            allTracks.concat(get(id));
        }

        return allTracks;
    }

    private SpotifyTracks get(String id) {
        buildHttpClient();
        RequestHeadersSpec<?> request = buildRequest(id);
        String responseJson = receiveResponse(request).block();

        return mapToPlaylist(responseJson);
    }

    private void buildHttpClient() {
        String baseURL = DotenvAdapter.get("API_PLAYLIST_URL");
        httpClient = WebClient.create(baseURL);
    }

    private RequestHeadersSpec<?> buildRequest(String id) {
        String fields = "items(track(album(artists,images)," +
                "duration_ms,name,id)),next";

        return httpClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/" + id + "/tracks")
                .queryParam("fields", fields)
                .queryParam("offset", offset)
                .build()
            )
            .header(
                "Authorization",
                "Bearer " + AccessTokenDAO.get().accessToken
            );
    }

    private Mono<String> receiveResponse(RequestHeadersSpec<?> request) {
        ResponseSpec response = request.retrieve();
        return SpotifyPlaylistResponseSpecBuilder.buildConstraints(response)
                .bodyToMono(String.class);
    }

    private SpotifyTracks mapToPlaylist(String responseJson) {
        return new Gson().fromJson(responseJson, SpotifyTracks.class);
    }
}
