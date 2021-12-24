// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackDAO;
import nl.th7mo.trackshop.api.track.TrackNotFoundException;
import nl.th7mo.trackshop.api.user.AppUser;

import nl.th7mo.trackshop.api.user.UserDAO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class LikedTracksService {

    private final TrackDAO trackDAO;
    private final UserDAO userDAO;

    public void post(String trackId, String currentUserEmailAddress)
    throws TrackNotFoundException {
        Track track = trackDAO.get(trackId);
        AppUser user = userDAO.get(currentUserEmailAddress);
        user.getLikedTracks().add(track);
    }

    public Set<Track> getAll(String currentUserEmailAddress) {
        AppUser user = userDAO.get(currentUserEmailAddress);

        return user.getLikedTracks();
    }

    public void deleteAll(String currentUserEmailAddress) {
        AppUser user = userDAO.get(currentUserEmailAddress);
        user.getLikedTracks().clear();
    }

    public void delete(String trackId, String currentUserEmailAddress)
    throws TrackNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);
        Track trackToBeDeleted = trackDAO.get(trackId);
        user.getLikedTracks().remove(trackToBeDeleted);
    }
}
