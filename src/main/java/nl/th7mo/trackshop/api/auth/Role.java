// XII·IX <> VII·X

package nl.th7mo.trackshop.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
}
