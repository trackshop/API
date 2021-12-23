// XII·IX <> VII·X

package nl.th7mo.trackshop.api.track;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Track {

    @Id
    private String id;

    private String name;
    private String artistName;
    private int duration;
    private double price;
    private String coverImageUrl;
}
