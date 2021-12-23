// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthorizationFilter extends OncePerRequestFilter {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private static String authorizationHeader;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        tryToAuthorize(filterChain);
    }

    private void tryToAuthorize(FilterChain filterChain)
    throws IOException, ServletException {
        if (doesNeedAuthorisation() && hasBearerHeader()) {
            try {
                Authorizer.authorize(authorizationHeader);
            } catch (Exception e) {
                ErrorResponseBuilder.build(e, response);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean doesNeedAuthorisation() {
        return !request.getServletPath().equals("/login");
    }

    private boolean hasBearerHeader() {
        authorizationHeader = request.getHeader(AUTHORIZATION);

        return authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer ");
    }
}
