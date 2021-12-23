// XII·IX <> VII·X

package nl.th7mo.trackshop.api.liked;

import nl.th7mo.trackshop.api.user.AppUser;
import nl.th7mo.trackshop.api.track.Track;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Table
@Getter
@Setter
public class LikedTrack {

    private Track likedTrack;
    private AppUser appUser;
}
