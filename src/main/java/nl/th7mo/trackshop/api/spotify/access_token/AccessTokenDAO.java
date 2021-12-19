// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.access_token;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

public final class AccessTokenDAO {

    private static WebClient httpClient;

    public static AccessToken get() {
        buildWebClient();
        RequestHeadersSpec<?> request = buildRequest();
        String responseJson = receiveResponse(request);

        return mapToAccessToken(responseJson);
    }

    private static void buildWebClient() {
        String baseURL = DotenvAdapter.get("API_BASE_URL");
        httpClient = WebClient.create(baseURL);
    }

    private static RequestHeadersSpec<?> buildRequest() {
        String tokenURL = DotenvAdapter.get("TOKEN_REQUEST_URL");

        return httpClient.post()
            .uri(tokenURL)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .header(
                    "Authorization",
                    "Basic " + CredentialEncoder.encodeBase64()
            )
            .body(BodyInserters.fromFormData(
                    "grant_type",
                    "client_credentials")
            );
    }

    private static String receiveResponse(RequestHeadersSpec<?> request) {
        return request.retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private static AccessToken mapToAccessToken(String responseJson) {
        return new Gson().fromJson(responseJson, AccessToken.class);
    }
}
