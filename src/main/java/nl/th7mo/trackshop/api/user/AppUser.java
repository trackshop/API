// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import nl.th7mo.trackshop.api.role.Role;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;

import lombok.Getter;
import lombok.Setter;
import nl.th7mo.trackshop.api.track.Track;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    private UUID id = UUID.randomUUID();

    private String emailAddress;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Track> likedTracks = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Track> shoppingCart = new HashSet<>();
}
