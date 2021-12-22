// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import nl.th7mo.trackshop.api.track.Track;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Playlist {

    @Id
    private String id;

    private String name;
    private int size;
    private String coverImageUrl;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist")
    private Set<Track> tracks = new HashSet<>();
}
