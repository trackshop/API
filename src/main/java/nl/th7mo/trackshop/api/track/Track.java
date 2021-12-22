// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;
import nl.th7mo.trackshop.api.playlist.Playlist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Track {

    @Id
    private String id;

    private String name;
    private String artistName;
    private int duration;
    private double price;
    private String coverImageUrl;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}
