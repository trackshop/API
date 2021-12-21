// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestParam String id) {
        playlistService.post(id);

        return "Spotify playlist with id '" + id + "' is inserted";
    }

    @GetMapping
    public List<Playlist> get() {
        return playlistService.get();
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id)
    throws PlaylistNotFoundException {
        playlistService.delete(id);
    }
}
