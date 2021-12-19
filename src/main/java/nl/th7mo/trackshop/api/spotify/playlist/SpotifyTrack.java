// XII·IX <> VII·X

package nl.th7mo.trackshop.api.spotify.playlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpotifyTrack {

    public String id;
    public List<SpotifyImage> images;

    public String name;
    public SpotifyAlbum album;

    @SerializedName("duration_ms")
    public int duration;

    public List<String> getArtistNames() {
        return album.artists.stream().map(artist -> artist.name).toList();
    }
}
