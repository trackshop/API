// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;

    public void post(AppUser user) {
        userRepository.save(user);
    }

    public AppUser get(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    public AppUser getByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    public List<AppUser> getAll() {
        return userRepository.findAll();
    }
}
