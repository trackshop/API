// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import java.util.List;

public class Playlist {

    public String name;
    private Tracks tracks;

    public List<Track> getTracks() {
        return tracks.items.stream().map(item -> item.track).toList();
    }

    private void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
