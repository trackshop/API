// XII·IX <> VII·X

package nl.th7mo.trackshop.api.playlist;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Playlist {

    @Id
    public String id;

    public String name;
    public int size;
    public String coverImageUrl;
}
