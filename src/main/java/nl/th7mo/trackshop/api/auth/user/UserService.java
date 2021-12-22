// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.user;

import nl.th7mo.trackshop.api.auth.role.Role;
import nl.th7mo.trackshop.api.auth.role.RoleDAO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public void post(AppUser user) {
        userDAO.post(user);
    }

    public void post(Role role) {
        roleDAO.post(role);
    }

    public void addRoleToUser(String emailAddress, String roleName) {
        AppUser user = userDAO.getByEmailAddress(emailAddress);
        Role role = roleDAO.getByName(roleName);

        user.getRoles().add(role);
    }

    public AppUser get(String emailAddress) {
        return userDAO.get(emailAddress);
    }

    public List<AppUser> getAll() {
        return userDAO.getAll();
    }
}
