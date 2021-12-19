// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Track {

    @Id
    public String id;

    public String name;
    public String artistName;
    public int duration;
    public double price;
    public String playlistId;
}
