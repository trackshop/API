// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TrackExceptionHandler {

    @ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "The id given is not a valid track id or the track" +
                "is not found"
    )
    @ExceptionHandler(TrackNotFoundException.class)
    public void handleException(TrackNotFoundException e) {}
}
