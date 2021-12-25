// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public final class Authorizer {

    public static void authorize(String authorizationHeader) {
        JwtTokenDecoder.decode(authorizationHeader);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(createAuthToken());
    }

    private static UsernamePasswordAuthenticationToken createAuthToken() {
        String userEmailAddress = JwtTokenDecoder.getUserEmailAddress();
        Collection<SimpleGrantedAuthority> grantedAuthoritiesOfUser =
            JwtTokenDecoder.getGrantedAuthoritiesOfUser();

        return new UsernamePasswordAuthenticationToken(
            userEmailAddress, null, grantedAuthoritiesOfUser
        );
    }
}
