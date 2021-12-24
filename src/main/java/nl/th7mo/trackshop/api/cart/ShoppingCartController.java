// XII·IX <> VII·X

package nl.th7mo.trackshop.api.cart;

import nl.th7mo.trackshop.api.track.TrackNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

     private final ShoppingCartService shoppingCartService;

     @ResponseStatus(value = HttpStatus.CREATED)
     @PostMapping("/{trackId}")
     public void post(
         @PathVariable String trackId,
         Authentication authentication
     ) throws TrackNotFoundException {
         shoppingCartService.post(trackId, authentication.getName());
     }
}
