// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyPlaylist;

import nl.th7mo.trackshop.api.track.Track;
import nl.th7mo.trackshop.api.track.TrackTranslator;

import java.util.Set;
import java.util.stream.Collectors;

public final class PlaylistTranslator {

    public static Playlist map(SpotifyPlaylist spotifyPlaylist) {
        Playlist playlist = new Playlist();
        playlist.setId(spotifyPlaylist.id);
        playlist.setName(spotifyPlaylist.name);
        playlist.setSize(spotifyPlaylist.getTracks().size());
        playlist.setCoverImageUrl(spotifyPlaylist.images.get(0).url);

        return addReferences(playlist, spotifyPlaylist);
    }

    private static Playlist addReferences(
        Playlist playlist,
        SpotifyPlaylist spotifyPlaylist
    ) {
        Set<Track> tracks = getTracks(spotifyPlaylist);
        tracks = TrackTranslator.addPlaylistReference(tracks, playlist);
        playlist.setTracks(tracks);

        return playlist;
    }

    private static Set<Track> getTracks(SpotifyPlaylist spotifyPlaylist) {
        return spotifyPlaylist.getTracks()
            .stream()
            .map(TrackTranslator::map)
            .collect(Collectors.toSet());
    }
}
