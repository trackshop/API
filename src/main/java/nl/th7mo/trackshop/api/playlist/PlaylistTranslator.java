// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackTranslator;
import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylist;

import java.util.List;

public final class PlaylistTranslator {

    public static Playlist map(SpotifyPlaylist spotifyPlaylist) {
        Playlist playlist = new Playlist();
        playlist.setId(spotifyPlaylist.id);
        playlist.setName(spotifyPlaylist.name);
        playlist.setSize(spotifyPlaylist.getTracks().size());
        playlist.setCoverImageUrl(spotifyPlaylist.images.get(0).url);
        playlist.setTracks(getTracks(spotifyPlaylist));

        return playlist;
    }

    private static List<Track> getTracks(SpotifyPlaylist spotifyPlaylist) {
        return spotifyPlaylist.getTracks()
            .stream()
            .map(TrackTranslator::map)
            .toList();
    }
}
