// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.role;

import nl.th7mo.trackshop.api.auth.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@RequestMapping("role")
public class RoleController {

    private final UserService userService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void post(@RequestBody Role role) {
        userService.post(role);
    }

    @PostMapping("/add-to-user")
    public void addRoleToUser(@RequestBody UserRole userRole) {
        userService.addRoleToUser(
            userRole.getEmailAddress(),
            userRole.getRoleName()
        );
    }
}
