// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import nl.th7mo.trackshop.api.user.AppUser;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/liked-tracks")
public class LikedTracksController {

    private final LikedTracksService tracksService;

    @PostMapping("/{trackId}")
    public void post(
        @PathVariable String trackId,
        @RequestBody AppUser user
    ) {
        tracksService.post(trackId, user);
    }
}
