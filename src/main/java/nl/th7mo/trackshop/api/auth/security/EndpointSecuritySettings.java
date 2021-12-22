// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

public final class EndpointSecuritySettings {

    private static HttpSecurity securitySettings;

    public static void setEndpointConstraints(HttpSecurity securitySettings)
    throws Exception {
        EndpointSecuritySettings.securitySettings = securitySettings;
        setUserEndpointConstraints();
        setPlaylistEndpointConstraints();
        setCartEndpointConstraints();
        setLikedTracksEndpointConstraints();
    }

    private static void setUserEndpointConstraints() throws Exception {
        setConstraint(GET, "/user/**", "ROLE_ADMIN");
        setConstraint(POST, "/role/**", "ROLE_ADMIN");
    }

    private static void setPlaylistEndpointConstraints() throws Exception {
        makeEndpointUnauthenticated(GET, "playlist/**");
        setConstraint(POST, "/playlist/**", "ROLE_ADMIN");
        setConstraint(DELETE, "/playlist/**", "ROLE_ADMIN");
    }

    private static void setCartEndpointConstraints() throws Exception {
        setConstraint(GET, "/cart/**", "ROLE_USER");
        setConstraint(POST, "/cart/**", "ROLE_USER");
        setConstraint(DELETE, "/cart/**", "ROLE_USER");
    }

    private static void setLikedTracksEndpointConstraints() throws Exception {
        setConstraint(GET, "/liked-tracks/**", "ROLE_USER");
        setConstraint(POST, "/liked-tracks/**", "ROLE_USER");
        setConstraint(DELETE, "/liked-tracks/**", "ROLE_USER");
    }

    private static void setConstraint(
        HttpMethod httpMethod, String endpoint, String role
    ) throws Exception {
        securitySettings.authorizeRequests()
            .antMatchers(httpMethod, endpoint)
            .hasAnyAuthority(role);
    }

    private static void makeEndpointUnauthenticated(
        HttpMethod httpMethod, String endpoint
    ) throws Exception {
        securitySettings.authorizeRequests()
            .antMatchers(httpMethod, endpoint)
            .permitAll();
    }
}
