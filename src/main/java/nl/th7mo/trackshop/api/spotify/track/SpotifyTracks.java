// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.track;

import java.util.List;

public class SpotifyTracks {

    public List<SpotifyItem> items;
    public String next;

    public void concat(SpotifyTracks tracks) {
        items.addAll(tracks.items);
    }
}
