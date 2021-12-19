// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylist;

import java.util.List;

public final class PlaylistTranslator {

    public static Playlist map(SpotifyPlaylist spotifyPlaylist) {
        Playlist playlist = new Playlist();
        playlist.setId(spotifyPlaylist.id);
        List<Track> tracks = spotifyPlaylist.getTracks()
                .stream()
                .map(TrackTranslator::map)
                .toList();
        playlist.setName(spotifyPlaylist.name);
        playlist.setSize(spotifyPlaylist.getTracks().size());
        playlist.setCoverImageUrl("");
        playlist.setTracks(tracks);

        return playlist;
    }


}
