// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import java.io.FileNotFoundException;

public class TrackNotFoundException extends FileNotFoundException {

    public TrackNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
