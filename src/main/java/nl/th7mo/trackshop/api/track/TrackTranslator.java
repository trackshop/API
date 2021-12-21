// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import nl.th7mo.trackshop.api.playlist.Playlist;
import nl.th7mo.trackshop.api.spotify.track.SpotifyTrack;
import nl.th7mo.trackshop.api.util.RandomNumberGenerator;

import java.util.Set;
import java.util.stream.Collectors;

public final class TrackTranslator {

    public static Track map(SpotifyTrack spotifyTrack) {
        Track track = new Track();
        track.setId(spotifyTrack.id);
        track.setName(spotifyTrack.name);
        track.setArtistName(spotifyTrack.getArtistNames().get(0));
        track.setDuration(spotifyTrack.duration);
        track.setPrice(RandomNumberGenerator.getRandomPrice(2, 5));
        track.setCoverImageUrl(spotifyTrack.album.images.get(0).url);

        return track;
    }

    public static Set<Track> addPlaylistReference(Set<Track> tracks, Playlist playlist) {
        return tracks.stream().map(
            track -> addPlaylistReference(track, playlist)
        ).collect(Collectors.toSet());
    }

    public static Track addPlaylistReference(Track track, Playlist playlist) {
        track.setPlaylist(playlist);

        return track;
    }
}
