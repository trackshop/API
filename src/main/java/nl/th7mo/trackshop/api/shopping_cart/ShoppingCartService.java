// XII·IX <> VII·X

package nl.th7mo.trackshop.api.shopping_cart;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackDAO;
import nl.th7mo.trackshop.api.track.TrackNotFoundException;
import nl.th7mo.trackshop.api.user.AppUser;
import nl.th7mo.trackshop.api.user.UserDAO;

import lombok.RequiredArgsConstructor;
import nl.th7mo.trackshop.api.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingCartService {

    private final UserDAO userDAO;
    private final TrackDAO trackDAO;

    public void post(String trackId, String currentUserEmailAddress)
    throws TrackNotFoundException, UserNotFoundException {
        Track track = trackDAO.get(trackId);
        AppUser user = userDAO.get(currentUserEmailAddress);
        user.getShoppingCart().add(track);
    }

    public Set<Track> getAll(String currentUserEmailAddress)
    throws UserNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);

        return user.getShoppingCart();
    }

    public void deleteAll(String currentUserEmailAddress)
    throws UserNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);
        user.getShoppingCart().clear();
    }

    public void delete(String trackId, String currentUserEmailAddress)
    throws TrackNotFoundException, UserNotFoundException {
        AppUser user = userDAO.get(currentUserEmailAddress);
        Track trackToBeDeleted = trackDAO.get(trackId);
        user.getShoppingCart().remove(trackToBeDeleted);
    }
}
