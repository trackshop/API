// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;


import nl.th7mo.trackshop.api.util.LogErrorAdapter;

public class InvalidSpotifyRequestException extends RuntimeException {

    public InvalidSpotifyRequestException(String errorMessage) {
        super(errorMessage);
        LogErrorAdapter.log(this.getClass());
    }
}
