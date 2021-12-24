// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class ErrorResponseBuilder {

    private static Exception jwtValidateException;
    private static final Map<String, String> errorResponseBody = new HashMap<>();

    public static void build(Exception jwtValidateException, HttpServletResponse response)
    throws IOException {
        ErrorResponseBuilder.jwtValidateException = jwtValidateException;
        String errorMessage = jwtValidateException.getMessage();
        response.setHeader("error", errorMessage);
        response.setStatus(FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        createResponseBody();

        new ObjectMapper().writeValue(response.getOutputStream(), errorResponseBody);
    }
    
    private static void createResponseBody() {
        errorResponseBody.put(
            "error_message",
            jwtValidateException.getMessage()
        );
    }
}
