// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PlaylistDAO {

    private final PlaylistRepository playlistRepository;

    public Set<Playlist> getAll() {
        return new HashSet<>(playlistRepository.findAll());
    }

    public Playlist get(String spotifyPlaylistId)
        throws PlaylistNotFoundException {
        return playlistRepository.findById(spotifyPlaylistId).orElseThrow(() ->
            new PlaylistNotFoundException(
                "Playlist with id '" + spotifyPlaylistId + "' is not found"
            )
        );
    }

    public void post(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void delete(String spotifyPlaylistId)
    throws PlaylistNotFoundException {
        try {
            playlistRepository.deleteById(spotifyPlaylistId);
        } catch (EmptyResultDataAccessException e) {
            throw new PlaylistNotFoundException(
                "Playlist with id '" + spotifyPlaylistId + "' is not found"
            );
        }
    }
}
