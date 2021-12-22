package nl.th7mo.trackshop.api;

import nl.th7mo.trackshop.api.auth.role.Role;
import nl.th7mo.trackshop.api.auth.user.AppUser;
import nl.th7mo.trackshop.api.auth.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            Role user = new Role();
            user.setName("ROLE_USER");

            Role admin = new Role();
            admin.setName("ROLE_ADMIN");
            userService.post(user);
            userService.post(admin);

            AppUser thimo = new AppUser();
            thimo.setEmailAddress("Thimo");
            thimo.setPassword("password");
            AppUser chris = new AppUser();
            chris.setEmailAddress("Chris");
            chris.setPassword("another");

            userService.post(thimo);
            userService.post(chris);

            userService.addRoleToUser("Thimo", "ROLE_ADMIN");
            userService.addRoleToUser("Thimo", "ROLE_USER");
            userService.addRoleToUser("Chris", "ROLE_USER");
        };
    }
}
