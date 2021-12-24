// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

public final class SpotifyPlaylistResponseSpecBuilder {

    public static ResponseSpec buildConstraints(ResponseSpec response) {
        return buildNotFoundConstraint(buildBadRequestConstraint(response));
    }

    private static ResponseSpec buildNotFoundConstraint(ResponseSpec responseSpec) {
        return responseSpec.onStatus(
            httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
            response -> Mono.error(
                new SpotifyPlaylistNotFoundException(
                    "The id given is not a valid spotify playlist id or the playlist is private"
                )
            )
        );
    }

    private static ResponseSpec buildBadRequestConstraint(ResponseSpec responseSpec) {
        return responseSpec.onStatus(
            httpStatus -> httpStatus.value() == HttpStatus.BAD_REQUEST.value(),
            response -> Mono.error(
                new InvalidSpotifyRequestException(
                    "The server made a bad request to the Spotify API"
                )
            )
        );
    }
}
