// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked_tracks;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackDAO;
import nl.th7mo.trackshop.api.track.TrackNotFoundException;
import nl.th7mo.trackshop.api.user.AppUser;

import nl.th7mo.trackshop.api.user.UserDAO;
import nl.th7mo.trackshop.api.user.UserNotFoundException;
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
    throws TrackNotFoundException, UserNotFoundException {
        Track track = trackDAO.get(trackId);
        AppUser user = userDAO.get(currentUserEmailAddress);
        user.getLikedTracks().add(track);
    }

    public Set<Track> getAll(String currentUserEmailAddress)
    throws UserNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);

        return user.getLikedTracks();
    }

    public void deleteAll(String currentUserEmailAddress)
    throws UserNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);
        user.getLikedTracks().clear();
    }

    public void delete(String trackId, String currentUserEmailAddress)
    throws TrackNotFoundException, UserNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);
        Track trackToBeDeleted = trackDAO.get(trackId);
        user.getLikedTracks().remove(trackToBeDeleted);
    }
}
