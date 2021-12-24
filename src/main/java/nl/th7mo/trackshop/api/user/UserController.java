// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import nl.th7mo.trackshop.api.role.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<AppUser> get() {
        return userService.getAll();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/signup")
    public void post(@RequestBody AppUser user)
    throws UserAlreadyExistsException, UserNotFoundException {
        try {
            userService.get(user.getEmailAddress());
            throw new UserAlreadyExistsException(
                "user with emailAddress '" + user.getEmailAddress() + "' already exists"
            );
        } catch (UserNotFoundException e) {
            userService.post(user);
            userService.addRoleToUser(user.getEmailAddress(), Roles.USER);
        }
    }
}
