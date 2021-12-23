// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.user;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<AppUser> get() {
        return userService.getAll();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void post(@RequestBody AppUser user) {
        userService.post(user);
    }
}
