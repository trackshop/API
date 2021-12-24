// XII·IX <> VII·X

package nl.th7mo.trackshop.api.cart;

import nl.th7mo.trackshop.api.user.AppUser;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

     private final ShoppingCartService shoppingCartService;

     @PostMapping("/{trackId}")
     public void post(
         @PathVariable String trackId,
         @RequestBody AppUser user
     ) {
         shoppingCartService.post(trackId, user);
     }
}
