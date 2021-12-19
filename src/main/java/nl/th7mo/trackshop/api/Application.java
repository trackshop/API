package nl.th7mo.trackshop.api;

import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Logger.getLogger("main").log(Level.WARNING, AccessTokenDAO.get().accessToken);
    }
}
