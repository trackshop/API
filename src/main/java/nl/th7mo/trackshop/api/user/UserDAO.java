// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDAO {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void post(AppUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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
