// XII·IX <> VII·X

package nl.th7mo.trackshop.api.shopping_cart;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackNotFoundException;

import nl.th7mo.trackshop.api.user.UserNotFoundException;
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
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public Set<Track> getAll(Authentication requestingUser)
    throws UserNotFoundException {
        return shoppingCartService.getAll(requestingUser.getName());
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/{trackId}")
    public void post(
        @PathVariable String trackId,
        Authentication requestingUser
    ) throws TrackNotFoundException, UserNotFoundException {
        shoppingCartService.post(trackId, requestingUser.getName());
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @DeleteMapping
    public void deleteAll(Authentication requestingUser)
    throws UserNotFoundException {
        shoppingCartService.deleteAll(requestingUser.getName());
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @DeleteMapping("/{trackId}")
    public void delete(
        @PathVariable String trackId,
        Authentication requestingUser
    ) throws TrackNotFoundException, UserNotFoundException {
        shoppingCartService.delete(trackId, requestingUser.getName());
    }
}
