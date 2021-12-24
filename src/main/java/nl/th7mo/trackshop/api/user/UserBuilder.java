// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import lombok.RequiredArgsConstructor;
import nl.th7mo.trackshop.api.role.Role;
import nl.th7mo.trackshop.api.util.LogErrorAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBuilder {

    private final UserDAO userDAO;

    public UserDetails build(String username)
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

        return buildUser(user);
    }

    private User buildUser(AppUser user) {
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
}
