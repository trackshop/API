// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import nl.th7mo.trackshop.api.role.Roles;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.DELETE;

public final class EndpointSecuritySettings {

    private static HttpSecurity securitySettings;

    public static void setEndpointConstraints(HttpSecurity securitySettings)
    throws Exception {
        EndpointSecuritySettings.securitySettings = securitySettings;
        setUserEndpointConstraints();
        setRoleEndpointConstraints();
        setPlaylistEndpointConstraints();
        setCartEndpointConstraints();
        setLikedTracksEndpointConstraints();
    }

    private static void setUserEndpointConstraints() throws Exception {
        setConstraint(GET, "/user/**", Roles.ADMIN);
        makeEndpointUnauthenticated(POST, "/signup/**");
    }

    private static void setRoleEndpointConstraints() throws Exception {
        setConstraint(POST, "/role/make-admin", Roles.SUPER_ADMIN);
        setConstraint(POST, "/role/revoke-admin", Roles.SUPER_ADMIN);
    }

    private static void setPlaylistEndpointConstraints() throws Exception {
        makeEndpointUnauthenticated(GET, "playlist/**");
        setConstraint(POST, "/playlist/**", Roles.ADMIN);
        setConstraint(DELETE, "/playlist/**", Roles.ADMIN);
    }

    private static void setCartEndpointConstraints() throws Exception {
        setConstraint(GET, "/cart/**", Roles.USER);
        setConstraint(POST, "/cart/**", Roles.USER);
        setConstraint(DELETE, "/cart/**", Roles.USER);
    }

    private static void setLikedTracksEndpointConstraints() throws Exception {
        setConstraint(GET, "/liked-tracks/**", Roles.USER);
        setConstraint(POST, "/liked-tracks/**", Roles.USER);
        setConstraint(DELETE, "/liked-tracks/**", Roles.USER);
    }

    private static void setConstraint(
        HttpMethod httpMethod, String endpoint, Roles role
    ) throws Exception {
        securitySettings.authorizeRequests()
            .antMatchers(httpMethod, endpoint)
            .hasAnyAuthority(role.value);
    }

    private static void makeEndpointUnauthenticated(
        HttpMethod httpMethod, String endpoint
    ) throws Exception {
        securitySettings.authorizeRequests()
            .antMatchers(httpMethod, endpoint)
            .permitAll();
    }
}
