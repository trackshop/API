// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.request;

import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;
import nl.th7mo.trackshop.api.util.DotenvAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

public final class SpotifyPlaylistRequestBuilder {

    private static WebClient httpClient;

    public static RequestHeadersSpec<?> build(String id) {
        buildHttpClient();
        String fields = "id,name,tracks(items(track(album(artists,images)," +
                        "duration_ms,name,id))),images";

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

    private static void buildHttpClient() {
        String baseURL = DotenvAdapter.get("API_PLAYLIST_URL");
        httpClient = WebClient.create(baseURL);
    }
}
