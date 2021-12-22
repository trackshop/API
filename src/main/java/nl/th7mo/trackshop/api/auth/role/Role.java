// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth.role;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Role {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
}
