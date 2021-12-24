// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;

import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest loginRequest,
        HttpServletResponse loginResponse
    ) throws AuthenticationException {
        return authenticationManager.authenticate(createAuthToken(loginRequest));
    }

    private UsernamePasswordAuthenticationToken createAuthToken(HttpServletRequest loginRequest) {
        String emailAddress = loginRequest.getParameter("emailAddress");
        String password = loginRequest.getParameter("password");

        return new UsernamePasswordAuthenticationToken(emailAddress, password);
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest loginRequest,
        HttpServletResponse loginResponse,
        FilterChain filterChain,
        Authentication authentication
    ) throws IOException {
        JwtTokenBuilder.build(loginResponse, authentication);
    }
}
