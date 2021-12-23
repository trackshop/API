// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import nl.th7mo.trackshop.api.spotify.track.SpotifyTrack;
import nl.th7mo.trackshop.api.util.RandomNumberGenerator;

public final class TrackTranslator {

    public static Track map(SpotifyTrack spotifyTrack) {
        Track track = new Track();
        track.setId(spotifyTrack.id);
        track.setName(spotifyTrack.name);
        track.setArtistName(spotifyTrack.getArtistNames().get(0));
        track.setDuration(spotifyTrack.duration);
        track.setPrice(RandomNumberGenerator.getRandomNumber(2, 5));
        track.setCoverImageUrl(spotifyTrack.album.images.get(0).url);

        return track;
    }
}
