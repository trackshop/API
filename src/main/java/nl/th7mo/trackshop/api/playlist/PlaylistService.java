// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylist;
import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistDAO;
import nl.th7mo.trackshop.api.track.Track;

import nl.th7mo.trackshop.api.user.UserService;
import nl.th7mo.trackshop.api.user.UserTrackService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistDAO playlistDAO;
    private final SpotifyPlaylistDAO spotifyPlaylistDAO;
    private final UserTrackService userTrackService;

    public void post(String spotifyPlaylistId) {
        SpotifyPlaylist spotifyPlaylist = spotifyPlaylistDAO.get(spotifyPlaylistId);
        post(PlaylistTranslator.map(spotifyPlaylist));
    }

    public void post(Playlist playlist) {
        playlistDAO.post(playlist);
    }

    public Set<Playlist> get() {
        return playlistDAO.get();
    }

    public void delete(String spotifyPlaylistId)
    throws PlaylistNotFoundException {
        Set<Track> trackToBeDeleted = playlistDAO.get(spotifyPlaylistId).getTracks();
        userTrackService.deleteTracks(trackToBeDeleted);
        playlistDAO.delete(spotifyPlaylistId);
    }
}
