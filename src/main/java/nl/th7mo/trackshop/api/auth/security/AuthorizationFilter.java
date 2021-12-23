// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

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
        if (doesNeedAuthorisation() && hasBearerHeader()) {
            try {
                authoriseUser();
            } catch (Exception e) {
                buildErrorResponse(e);
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

    private void authoriseUser() {
        JwtTokenDecoder.decode(authorizationHeader);
        SecurityContextHolder.getContext().setAuthentication(createAuthToken());
    }

    private UsernamePasswordAuthenticationToken createAuthToken() {
        String userEmailAddress = JwtTokenDecoder.getUserEmailAddress();

        return new UsernamePasswordAuthenticationToken(
            userEmailAddress, null, JwtTokenDecoder.getAuthoritiesOfUser()
        );
    }

    private void buildErrorResponse(Exception e) throws IOException {
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> errorResponseBody = new HashMap<>();
        errorResponseBody.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), errorResponseBody);
    }
}
