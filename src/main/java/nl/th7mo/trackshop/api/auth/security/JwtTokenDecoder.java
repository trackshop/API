// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;

public final class JwtTokenDecoder {

    private static DecodedJWT decodedJWT;

    public static void decode(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm tokenEncryptAlgorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier jwtVerifier = JWT.require(tokenEncryptAlgorithm).build();
        JwtTokenDecoder.decodedJWT = jwtVerifier.verify(token);
    }

    public static Collection<SimpleGrantedAuthority> getAuthoritiesOfUser() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        stream(roles).forEach(
            role -> authorities.add(new SimpleGrantedAuthority(role))
        );

        return authorities;
    }

    public static String getUserEmailAddress() {
        return decodedJWT.getSubject();
    }
}
