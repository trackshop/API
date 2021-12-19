package nl.th7mo.trackshop.api;

import nl.th7mo.trackshop.api.spotify.playlist.PlaylistDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        PlaylistDAO.get("2CLV0KGCl0UwTvipE4Ibss");
    }
}
