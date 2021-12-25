// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class JwtErrorResponseWriter {

    private static ResponseWriter responseWriter;
    private static HttpServletResponse loginResponse;
    private static Exception jwtValidateException;
    private static String errorMessage;

    public static void write(
        Exception jwtValidateException,
        HttpServletResponse loginResponse
    ) throws IOException {
        JwtErrorResponseWriter.jwtValidateException = jwtValidateException;
        JwtErrorResponseWriter.loginResponse = loginResponse;
        responseWriter = new ResponseWriter();
        setupResponse();
        createResponse();
        writeResponse();
    }

    private static void setupResponse() {
        errorMessage = jwtValidateException.getMessage();
        loginResponse.setHeader("error", errorMessage);
        loginResponse.setStatus(FORBIDDEN.value());
        loginResponse.setContentType(APPLICATION_JSON_VALUE);
    }

    private static void createResponse() {
        responseWriter.addToResponseBody("error_message", errorMessage);
    }

    private static void writeResponse() throws IOException {
        responseWriter.write(loginResponse.getOutputStream());
    }
}
