// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.track.TrackDAO;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PlaylistDAO {

    private final PlaylistRepository playlistRepository;
    private final TrackDAO trackDAO;

    @Autowired
    public PlaylistDAO(PlaylistRepository playlistRepository, TrackDAO trackDAO) {
        this.playlistRepository = playlistRepository;
        this.trackDAO = trackDAO;
    }

    public void post(Playlist playlist) {
        playlistRepository.save(playlist);
        trackDAO.post(playlist.getTracks());
    }
}
