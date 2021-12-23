// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackDAO;
import nl.th7mo.trackshop.api.user.AppUser;

import nl.th7mo.trackshop.api.user.UserDAO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikedTracksService {

    private final TrackDAO trackDAO;
    private final UserDAO userDAO;

    public void post(String trackId, AppUser searchUser) {
        Track track = trackDAO.get(trackId);
        AppUser user = userDAO.get(searchUser.getEmailAddress());
        user.getLikedSongs().add(track);
    }
}
