// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;
import java.util.LinkedHashMap;

public final class ResponseWriter {

    private final ObjectMapper responseWriter = new ObjectMapper();
    private final Map<String, Object> responseBody = new LinkedHashMap<>();

    public void write(OutputStream outputStream) throws IOException {
        responseWriter.writeValue(outputStream, responseBody);
    }

    public void addToResponseBody(String key, Object value) {
        responseBody.put(key, value);
    }
}
