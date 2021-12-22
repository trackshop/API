// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JwtTokenBuilder {

    private static HttpServletResponse response;

    public static void build(
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException {
        JwtTokenBuilder.response = response;
        User loggedInUser = (User) authentication.getPrincipal();
        int oneHourInMilles = 60 * 60 + 1000;
        String accessToken = buildAccessToken(loggedInUser, oneHourInMilles);
        String refreshToken = buildAccessToken(loggedInUser, oneHourInMilles * 6);
        setResponseBody(accessToken, refreshToken);
    }

    private static void setResponseBody(String accessToken, String refreshToken)
        throws IOException {
        Map<String, String> JwtTokens = new HashMap<>();
        JwtTokens.put("access_token", accessToken);
        JwtTokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), JwtTokens);
    }

    private static String buildAccessToken(User loggedInUser, int expireDuration) {
        Algorithm tokenEncryptAlgorithm = Algorithm.HMAC256("secret".getBytes());
        Date expireDate = new Date(System.currentTimeMillis() + expireDuration);

        return JWT.create()
            .withSubject(loggedInUser.getUsername())
            .withExpiresAt(expireDate)
            .withClaim("roles", getAuthoritiesFromLoggedInUser(loggedInUser))
            .sign(tokenEncryptAlgorithm);
    }

    private static List<String> getAuthoritiesFromLoggedInUser(User loggedInUser) {
        return loggedInUser.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority).toList();
    }
}
