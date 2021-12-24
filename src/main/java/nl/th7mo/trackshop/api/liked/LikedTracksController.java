// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import nl.th7mo.trackshop.api.track.TrackNotFoundException;
import nl.th7mo.trackshop.api.user.AppUser;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/liked-tracks")
public class LikedTracksController {

    private final LikedTracksService tracksService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{trackId}")
    public void post(
        @PathVariable String trackId,
        Authentication authentication
    ) throws TrackNotFoundException {
        tracksService.post(trackId, authentication.getName());
    }
}
