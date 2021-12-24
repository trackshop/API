// XII·IX <> VII·X

package nl.th7mo.trackshop.api.role;

import nl.th7mo.trackshop.api.user.AppUser;
import nl.th7mo.trackshop.api.user.UserNotFoundException;
import nl.th7mo.trackshop.api.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final UserService userService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void post(@RequestBody Role role) {
        userService.post(role);
    }

    @PostMapping("/make-admin")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void makeAdmin(
        @RequestBody AppUser loggedInUser,
        Authentication authentication
    )
    throws UserNotFoundException {
        if (!superAdminTriesToModifyOwnPrivilege(authentication, loggedInUser))
        userService.addRoleToUser(
            loggedInUser.getEmailAddress(),
            Roles.ADMIN
        );
    }

    @PostMapping("/revoke-admin")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void revokeAdmin(
        @RequestBody AppUser loggedInUser,
        Authentication authentication
    ) throws UserNotFoundException, CantRevokeSuperAdminPrivilegeException {
        if (superAdminTriesToModifyOwnPrivilege(authentication, loggedInUser)) {
            throw new CantRevokeSuperAdminPrivilegeException(
                "The SUPER_ADMIN can't revoke roles of itself"
            );
        }

        userService.revokeRoleFromUser(
            loggedInUser.getEmailAddress(),
            Roles.ADMIN
        );
    }

    private boolean superAdminTriesToModifyOwnPrivilege(
        Authentication authentication,
        AppUser loggedInUser
    ) {
        return authentication.getName().equals(loggedInUser.getEmailAddress());
    }
}
