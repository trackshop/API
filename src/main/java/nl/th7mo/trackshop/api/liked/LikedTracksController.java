// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/liked-tracks")
public class LikedTracksController {

    private final LikedTracksService likedTracksService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{trackId}")
    public void post(
        @PathVariable String trackId,
        Authentication authentication
    ) throws TrackNotFoundException {
        likedTracksService.post(trackId, authentication.getName());
    }

    @GetMapping
    public Set<Track> getAll(Authentication authentication) {
        return likedTracksService.getAll(authentication.getName());
    }

    @DeleteMapping
    @RequestMapping("/{trackId}")
    public void delete(
        @PathVariable String trackId,
        Authentication authentication
    ) throws TrackNotFoundException {
        likedTracksService.delete(trackId, authentication.getName());
    }

    @DeleteMapping
    public void deleteAll(Authentication authentication) {
        likedTracksService.deleteAll(authentication.getName());
    }
}
