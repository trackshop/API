// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import nl.th7mo.trackshop.api.util.LogErrorAdapter;

import java.io.FileNotFoundException;

public class TrackNotFoundException extends FileNotFoundException {

    public TrackNotFoundException(String errorMessage) {
        super(errorMessage);
        LogErrorAdapter.log(this.getClass());
    }
}
