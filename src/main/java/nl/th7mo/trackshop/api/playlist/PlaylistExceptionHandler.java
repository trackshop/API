// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.InvalidSpotifyRequestException;
import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistNotFoundException;

import nl.th7mo.trackshop.api.util.ExceptionResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class PlaylistExceptionHandler {

    @ExceptionHandler(SpotifyPlaylistNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        SpotifyPlaylistNotFoundException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        PlaylistNotFoundException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(InvalidSpotifyRequestException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        InvalidSpotifyRequestException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
}
