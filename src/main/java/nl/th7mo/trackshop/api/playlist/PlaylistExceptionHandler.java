// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlaylistExceptionHandler {

    @ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "The id given is not a valid spotify playlist id or " +
                "the playlist is private"
    )
    @ExceptionHandler(SpotifyPlaylistNotFoundException.class)
    public void handleException(SpotifyPlaylistNotFoundException e) {
    }
}
