// XII·IX <> VII·X

package nl.th7mo.trackshop.api.cart;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackDAO;
import nl.th7mo.trackshop.api.user.AppUser;
import nl.th7mo.trackshop.api.user.UserDAO;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
public class ShoppingCartService {

    private final UserDAO userDAO;
    private final TrackDAO trackDAO;

    public void post(String trackId, AppUser searchUser) {
        Track track = trackDAO.get(trackId);
        AppUser user = userDAO.get(searchUser.getEmailAddress());
        user.getShoppingCart().add(track);
    }
}
