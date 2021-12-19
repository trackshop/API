// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import nl.th7mo.trackshop.api.spotify.playlist.SpotifyTrack;

public class TrackTranslator {

    public static Track map(SpotifyTrack spotifyTrack) {
        Track track = new Track();
        track.setId(spotifyTrack.id);
        track.setName(spotifyTrack.name);
        track.setArtistName(spotifyTrack.getArtistNames().get(0));
        track.setDuration(spotifyTrack.duration);
        track.setPrice(getRandomPrice(2, 5));
        track.setCoverImageUrl(spotifyTrack.album.images.get(0).url);

        return track;
    }

    private static double getRandomPrice(int minPrice, int maxPrice) {
        double number = Math.random() * (maxPrice - minPrice);

        return Math.round((minPrice + number) * 100) / 100.0;
    }
}
