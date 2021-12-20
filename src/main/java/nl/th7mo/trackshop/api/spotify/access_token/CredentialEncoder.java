// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.access_token;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
final class CredentialEncoder {

    static String encodeBase64() {
        String clientId = DotenvAdapter.get("CLIENT_ID");
        String clientSecret = DotenvAdapter.get("CLIENT_SECRET");
        Base64.Encoder encoder = Base64.getEncoder();
        String clientCredentials = clientId + ":" + clientSecret;

        return encoder.encodeToString(clientCredentials.getBytes());
    }
}
