// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.track.TrackDAO;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaylistDAO {

    private final PlaylistRepository playlistRepository;
    private final TrackDAO trackDAO;

    public void post(Playlist playlist) {
        playlistRepository.save(playlist);
        trackDAO.post(playlist.getTracks());
    }

    public List<Playlist> get() {
        return playlistRepository.findAll();
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
