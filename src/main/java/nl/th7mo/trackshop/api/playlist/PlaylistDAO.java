// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistDAO {

    private final PlaylistRepository playlistRepository;
    private final TrackDAO trackDAO;

    @Autowired
    public PlaylistDAO(PlaylistRepository playlistRepository, TrackDAO trackDAO) {
        this.playlistRepository = playlistRepository;
        this.trackDAO = trackDAO;
    }

    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
        trackDAO.post(playlist.getTracks());
    }
}
