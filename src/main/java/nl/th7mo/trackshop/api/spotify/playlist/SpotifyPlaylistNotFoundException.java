// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "The id given is not a valid spotify playlist id or " +
            "the playlist is private"
)
public class SpotifyPlaylistNotFoundException extends FileNotFoundException {

    public SpotifyPlaylistNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
