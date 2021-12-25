// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;

import java.util.List;
import java.util.Date;

public class JwtTokenBuilder {

    private static User loggedInUser;

    public static String build(Authentication authentication) throws IOException {
        loggedInUser = (User) authentication.getPrincipal();

        return buildAccessToken();
    }

    private static String buildAccessToken() {
        String jwtTokenSecret = DotenvAdapter.get("JWT_TOKEN_SECRET");
        Algorithm tokenEncryptAlgorithm = Algorithm.HMAC256(jwtTokenSecret.getBytes());

        return JWT.create()
            .withSubject(loggedInUser.getUsername())
            .withExpiresAt(getJwtTokenExpireDate())
            .withClaim("roles", getGrantedAuthoritiesFromUser())
            .sign(tokenEncryptAlgorithm);
    }

    private static Date getJwtTokenExpireDate() {
        int oneHourInMilliseconds = 60 * 60 * 1000;

        return new Date(System.currentTimeMillis() + oneHourInMilliseconds);
    }

    private static List<String> getGrantedAuthoritiesFromUser() {
        return loggedInUser.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority).toList();
    }
}
