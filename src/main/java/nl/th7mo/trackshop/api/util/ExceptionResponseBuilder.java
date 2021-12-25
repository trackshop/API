// XII·IX <> VII·X

package nl.th7mo.trackshop.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExceptionResponseBuilder {

    public static ResponseEntity<Map<String, Object>> build(
        HttpStatus status, Exception exception
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", exception.getMessage());

        return ResponseEntity.status(status).body(body);
    }
}
