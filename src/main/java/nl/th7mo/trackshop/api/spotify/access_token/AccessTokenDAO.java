// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.access_token;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

public final class AccessTokenDAO {

    private static WebClient webClient;

    public static AccessToken get() {
        buildWebClient();
        RequestHeadersSpec<?> request = buildRequest();

        return receiveResponse(request);
    }

    private static void buildWebClient() {
        String baseURL = DotenvAdapter.get("API_BASE_URL");
        webClient = WebClient.create(baseURL);
    }

    private static RequestHeadersSpec<?> buildRequest() {
        String tokenURL = DotenvAdapter.get("TOKEN_REQUEST_URL");

        return webClient.post()
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

    private static AccessToken receiveResponse(RequestHeadersSpec<?> request) {
        String responseJson = request.retrieve()
                .bodyToMono(String.class)
                .block();

        return new Gson().fromJson(responseJson, AccessToken.class);
    }
}
