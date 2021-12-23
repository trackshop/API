// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.role;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
}
