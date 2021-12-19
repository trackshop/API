package nl.th7mo.trackshop.api;

import com.google.gson.Gson;
import nl.th7mo.trackshop.api.spotify.access_token.AccessTokenDAO;
import nl.th7mo.trackshop.api.spotify.playlist.Playlist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
