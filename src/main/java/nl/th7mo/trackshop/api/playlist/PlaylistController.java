// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestParam String id) {
        playlistService.post(id);

        return "Spotify playlist with id '" + id + "' is inserted";
    }

    @PostMapping("/new")
    public void post(@RequestBody Playlist playlist) {
        playlistService.post(playlist);
    }

    @GetMapping
    public Set<Playlist> get() {
        return playlistService.get();
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id)
    throws PlaylistNotFoundException {
        playlistService.delete(id);
    }
}
