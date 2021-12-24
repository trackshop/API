// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import nl.th7mo.trackshop.api.util.DotenvAdapter;

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
        String accessToken = buildAccessToken(loggedInUser);
        setResponseBody(accessToken);
    }

    private static void setResponseBody(String accessToken)
        throws IOException {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("access_token", accessToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

    private static String buildAccessToken(User loggedInUser) {
        String jwtTokenSecret = DotenvAdapter.get("JWT_TOKEN_SECRET");
        Algorithm tokenEncryptAlgorithm = Algorithm.HMAC256(jwtTokenSecret.getBytes());

        return JWT.create()
            .withSubject(loggedInUser.getUsername())
            .withExpiresAt(getExpireTime())
            .withClaim("roles", getAuthoritiesFromLoggedInUser(loggedInUser))
            .sign(tokenEncryptAlgorithm);
    }

    private static Date getExpireTime() {
        int oneHourInMilles = 60 * 60 * 1000;

        return new Date(System.currentTimeMillis() + oneHourInMilles);
    }

    private static List<String> getAuthoritiesFromLoggedInUser(User loggedInUser) {
        return loggedInUser.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority).toList();
    }
}
