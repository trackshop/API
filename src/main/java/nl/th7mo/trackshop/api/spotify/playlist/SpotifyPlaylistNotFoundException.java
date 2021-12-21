// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import nl.th7mo.trackshop.api.util.LogErrorAdapter;

import java.io.FileNotFoundException;

public class SpotifyPlaylistNotFoundException extends FileNotFoundException {

    public SpotifyPlaylistNotFoundException(String errorMessage) {
        super(errorMessage);
        LogErrorAdapter.log(this.getClass());
    }
}
