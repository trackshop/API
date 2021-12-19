// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public final class SpotifyPlaylistDAO {

    private static WebClient httpClient;

    public static SpotifyPlaylist get(String id) {
        buildHttpClient();
        WebClient.RequestHeadersSpec<?> request = buildRequest(id);
        String responseJson = receiveResponse(request);

        return mapToPlaylist(responseJson);
    }

    private static void buildHttpClient() {
        String baseURL = DotenvAdapter.get("API_PLAYLIST_URL");
        httpClient = WebClient.create(baseURL);
    }

    private static WebClient.RequestHeadersSpec<?> buildRequest(String id) {
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

    private static String receiveResponse(WebClient.RequestHeadersSpec<?> request) {
        return request.retrieve()
            .bodyToMono(String.class)
            .block();
    }

    private static SpotifyPlaylist mapToPlaylist(String responseJson) {
        Logger.getLogger("f").log(Level.WARNING, responseJson);
        return new Gson().fromJson(responseJson, SpotifyPlaylist.class);
    }
}
