// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import nl.th7mo.trackshop.api.util.ExceptionResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class TrackExceptionHandler {

    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        TrackNotFoundException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.NOT_FOUND, e);
    }
}
