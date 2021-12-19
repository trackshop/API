// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylist;
import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylistDAO;
import nl.th7mo.trackshop.api.spotify.playlist.SpotifyTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlaylistController {

    private final PlaylistDAO playlistDAO;

    @Autowired
    public PlaylistController(PlaylistDAO playlistDAO) {
        List<Track> tracks = new ArrayList<>();
        this.playlistDAO = playlistDAO;
        SpotifyPlaylist spotifyPlaylist = SpotifyPlaylistDAO.get("4MWtutmJdwJLX8p1SsTQal");
        for (SpotifyTrack track : spotifyPlaylist.getTracks()) {
            tracks.add(TrackTranslator.map(track));
        }

        Playlist playlist = PlaylistTranslator.map(spotifyPlaylist);

        for (Track track : tracks) {
            track.setPlaylist(playlist);
        }

        playlist.setTracks(tracks);
        playlistDAO.savePlaylist(playlist);
    }
}
