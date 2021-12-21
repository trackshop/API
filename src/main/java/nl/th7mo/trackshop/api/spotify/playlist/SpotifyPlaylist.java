// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import java.util.List;

public class SpotifyPlaylist {

    public String id;
    public String name;
    private SpotifyTracks tracks;

    public List<SpotifyTrack> getTracks() {
        return tracks.items.stream().map(item -> item.track).toList();
    }

    public void setTracks(SpotifyTracks tracks) {
        this.tracks = tracks;
    }
}
