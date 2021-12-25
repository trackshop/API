// XII·IX <> VII·X

package nl.th7mo.trackshop.api;

import nl.th7mo.trackshop.api.role.Role;
import nl.th7mo.trackshop.api.role.Roles;
import nl.th7mo.trackshop.api.user.AppUser;
import nl.th7mo.trackshop.api.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            for (Roles role : Roles.values()) {
                userService.post(new Role(role.value));
            }

            AppUser thimo = new AppUser();
            thimo.setEmailAddress("thimoquinten@gmail.com");
            thimo.setPassword("password");

            userService.post(thimo);
            userService.addRoleToUser("thimoquinten@gmail.com", Roles.SUPER_ADMIN);
            userService.addRoleToUser("thimoquinten@gmail.com", Roles.ADMIN);
            userService.addRoleToUser("thimoquinten@gmail.com", Roles.USER);
        };
    }
}
