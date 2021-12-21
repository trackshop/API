// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.request;

import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;
import nl.th7mo.trackshop.api.util.DotenvAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

public final class SpotifyTrackRequestBuilder {

    private static WebClient httpClient;

    public static RequestHeadersSpec<?> build(String id, int offset) {
        buildHttpClient();
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

    private static void buildHttpClient() {
        String baseURL = DotenvAdapter.get("API_PLAYLIST_URL");
        httpClient = WebClient.create(baseURL);
    }
}
