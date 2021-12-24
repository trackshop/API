// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import nl.th7mo.trackshop.api.track.Track;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTrackService {

    private final UserService userService;

    public void deleteTracks(Set<Track> tracksToBeDeleted) {
        userService.getAll().forEach( user ->
            deleteTracksInUser(tracksToBeDeleted, user)
        );
    }

    private void deleteTracksInUser(Set<Track> tracks, AppUser user) {
        List<String> trackIds = getTrackIdsFromTracks(tracks);
        user.setLikedTracks(getFilteredTracks(trackIds, user.getLikedTracks()));
        user.setShoppingCart(getFilteredTracks(trackIds, user.getShoppingCart()));
    }

    private List<String> getTrackIdsFromTracks(Set<Track> tracks) {
        return tracks.stream().map(Track::getId).toList();
    }

    private Set<Track> getFilteredTracks(List<String> trackIds, Set<Track> tracksFromUser) {
        tracksFromUser.removeIf(track -> trackIds.contains(track.getId()));

        return tracksFromUser;
    }
}
