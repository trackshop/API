// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class ErrorResponseBuilder {

    private static Exception jwtValidateException;
    private static final Map<String, String> errorResponseBody = new HashMap<>();

    public static void build(Exception jwtValidateException, HttpServletResponse loginResponse)
    throws IOException {
        ErrorResponseBuilder.jwtValidateException = jwtValidateException;
        String errorMessage = jwtValidateException.getMessage();
        loginResponse.setHeader("error", errorMessage);
        loginResponse.setStatus(FORBIDDEN.value());
        loginResponse.setContentType(APPLICATION_JSON_VALUE);
        createResponseBody();

        new ObjectMapper().writeValue(loginResponse.getOutputStream(), errorResponseBody);
    }
    
    private static void createResponseBody() {
        errorResponseBody.put(
            "error_message",
            jwtValidateException.getMessage()
        );
    }
}
