// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylist;
import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistDAO;

import nl.th7mo.trackshop.api.track.TrackDAO;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class PlaylistService {

    private final PlaylistDAO playlistDAO;
    private final TrackDAO trackDAO;
    private final SpotifyPlaylistDAO spotifyPlaylistDAO;

    @Autowired
    public PlaylistService(
        PlaylistDAO playlistDAO,
        TrackDAO trackDAO,
        SpotifyPlaylistDAO spotifyPlaylistDAO
    ) {
        this.playlistDAO = playlistDAO;
        this.trackDAO = trackDAO;
        this.spotifyPlaylistDAO = spotifyPlaylistDAO;
    }

    public void post(String spotifyPlaylistId) {
        SpotifyPlaylist spotifyPlaylist = spotifyPlaylistDAO.get(spotifyPlaylistId);
        post(PlaylistTranslator.map(spotifyPlaylist));
    }

    private void post(Playlist playlist) {
        playlistDAO.post(playlist);
        trackDAO.post(playlist.getTracks());
    }

    public List<Playlist> get() {
        return playlistDAO.get();
    }

    public void delete(String spotifyPlaylistId)
    throws PlaylistNotFoundException {
        playlistDAO.delete(spotifyPlaylistId);
    }
}
