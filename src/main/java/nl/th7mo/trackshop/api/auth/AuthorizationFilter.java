// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthorizationFilter extends OncePerRequestFilter {

    private HttpServletRequest loginRequest;
    private HttpServletResponse loginResponse;
    private String authorizationHeader;

    @Override
    protected void doFilterInternal(
        HttpServletRequest loginRequest,
        HttpServletResponse loginResponse,
        FilterChain filterChain
    ) throws IOException, ServletException {
        this.loginRequest = loginRequest;
        this.loginResponse = loginResponse;
        tryToAuthorize(filterChain);
    }

    private void tryToAuthorize(FilterChain filterChain)
    throws IOException, ServletException {
        if (doesNeedAuthorisation() && hasBearerHeader()) {
            try {
                Authorizer.authorize(authorizationHeader);
            } catch (Exception jwtValidateException) {
                JwtErrorResponseWriter.write(jwtValidateException, loginResponse);
                return;
            }
        }

        filterChain.doFilter(loginRequest, loginResponse);
    }

    private boolean doesNeedAuthorisation() {
        return !loginRequest.getServletPath().equals("/login");
    }

    private boolean hasBearerHeader() {
        authorizationHeader = loginRequest.getHeader(AUTHORIZATION);

        return authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer ");
    }
}
