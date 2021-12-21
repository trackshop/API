// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import java.io.FileNotFoundException;

public class SpotifyPlaylistNotFoundException extends FileNotFoundException {

    public SpotifyPlaylistNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
