// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public final class Authorizer {

    public static void authorize(String authorizationHeader) {
        JwtTokenDecoder.decode(authorizationHeader);
        SecurityContextHolder.getContext().setAuthentication(createAuthToken());
    }

    private static UsernamePasswordAuthenticationToken createAuthToken() {
        String userEmailAddress = JwtTokenDecoder.getUserEmailAddress();

        return new UsernamePasswordAuthenticationToken(
            userEmailAddress, null, JwtTokenDecoder.getAuthoritiesOfUser()
        );
    }
}
