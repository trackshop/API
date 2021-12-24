// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import nl.th7mo.trackshop.api.role.Role;
import nl.th7mo.trackshop.api.role.RoleDAO;

import nl.th7mo.trackshop.api.role.Roles;
import nl.th7mo.trackshop.api.util.LogErrorAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
        AppUser user = null;
        try {
            user = userDAO.get(username);
        } catch (UserNotFoundException e) {
            LogErrorAdapter.log(e.getClass());
        }

        if (user == null) {
            throw new UsernameNotFoundException(
                "User '" + username + "' not found in database"
            );
        }

        return new User(
            user.getEmailAddress(),
            user.getPassword(),
            getAuthorities(user)
        );
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(AppUser user) {
        Collection<Role> roles = user.getRoles();
        List<String> roleNames = roles.stream().map(Role::getName).toList();

        return roleNames.stream().map(SimpleGrantedAuthority::new).toList();
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
