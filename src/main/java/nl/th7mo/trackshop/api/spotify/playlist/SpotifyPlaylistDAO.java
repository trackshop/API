// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.google.gson.Gson;
import reactor.core.publisher.Mono;

@Component
public final class SpotifyPlaylistDAO {

    private WebClient httpClient;

    public SpotifyPlaylist get(String id) {
        buildHttpClient();
        WebClient.RequestHeadersSpec<?> request = buildRequest(id);
        String responseJson = receiveResponse(request).block();

        return mapToPlaylist(responseJson);
    }

    private void buildHttpClient() {
        String baseURL = DotenvAdapter.get("API_PLAYLIST_URL");
        httpClient = WebClient.create(baseURL);
    }

    private WebClient.RequestHeadersSpec<?> buildRequest(String id) {
        String fields = "id,name,tracks(items(track(album(artists,images)," +
                        "duration_ms,name,id)))";

        return httpClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/" + id)
                .queryParam("fields", fields)
                .build()
            )
            .header(
                "Authorization",
                "Bearer " + AccessTokenDAO.get().accessToken
            );
    }

    private Mono<String> receiveResponse(WebClient.RequestHeadersSpec<?> request) {
        ResponseSpec response = request.retrieve();
        return SpotifyPlaylistResponseSpecBuilder.buildConstraints(response)
            .bodyToMono(String.class);
    }

    private SpotifyPlaylist mapToPlaylist(String responseJson) {
        return new Gson().fromJson(responseJson, SpotifyPlaylist.class);
    }
}
