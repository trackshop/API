// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
        post("2CLV0KGCl0UwTvipE4Ibss");
    }

    @PostMapping
    public void post(String spotifyPlaylistId) {
        playlistService.post(spotifyPlaylistId);
    }

//    @GetMapping
//    public Playlist getPlaylist() {
//
//    }
}
