// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import java.io.FileNotFoundException;

public class PlaylistNotFoundException extends FileNotFoundException {

    public PlaylistNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
