// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.user;

import lombok.Data;
import nl.th7mo.trackshop.api.auth.role.Role;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
public class AppUser {

    @Id
    private UUID id = UUID.randomUUID();

    private String emailAddress;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
