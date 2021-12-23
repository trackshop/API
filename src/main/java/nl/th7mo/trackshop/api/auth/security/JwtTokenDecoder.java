// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import nl.th7mo.trackshop.api.util.DotenvAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static java.util.Arrays.stream;

public final class JwtTokenDecoder {

    private static DecodedJWT decodedJWT;

    public static void decode(String authorizationHeader) {
        String jwtToken = authorizationHeader.substring("Bearer ".length());
        String jwtTokenSecret = DotenvAdapter.get("JWT_TOKEN_SECRET");
        Algorithm tokenEncryptAlgorithm = Algorithm.HMAC256(jwtTokenSecret.getBytes());
        JWTVerifier jwtVerifier = JWT.require(tokenEncryptAlgorithm).build();
        JwtTokenDecoder.decodedJWT = jwtVerifier.verify(jwtToken);
    }

    public static Collection<SimpleGrantedAuthority> getAuthoritiesOfUser() {
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

        return stream(roles).map(SimpleGrantedAuthority::new).toList();
    }

    public static String getUserEmailAddress() {
        return decodedJWT.getSubject();
    }
}
