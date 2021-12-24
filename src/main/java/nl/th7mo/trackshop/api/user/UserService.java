// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import nl.th7mo.trackshop.api.role.Role;
import nl.th7mo.trackshop.api.role.RoleDAO;
import nl.th7mo.trackshop.api.role.Roles;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final UserBuilder userBuilder;

    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
        return userBuilder.build(username);
    }

    public void post(AppUser user) {
        userDAO.post(user);
    }

    public void post(Role role) {
        roleDAO.post(role);
    }

    public void addRoleToUser(String emailAddress, Roles role)
    throws UserNotFoundException {
        AppUser user = userDAO.get(emailAddress);
        Role roleForUser = roleDAO.getByName(role.value);
        user.getRoles().add(roleForUser);
        System.out.println(user.getRoles());
    }

    public void revokeRoleFromUser(String emailAddress, Roles role)
    throws UserNotFoundException {
        AppUser user = userDAO.get(emailAddress);
        Role roleRemovingFromUser = roleDAO.getByName(role.value);
        user.getRoles().remove(roleRemovingFromUser);
    }

    public AppUser get(String emailAddress) throws UserNotFoundException {
        return userDAO.get(emailAddress);
    }

    public List<AppUser> getAll() {
        return userDAO.getAll();
    }
}
