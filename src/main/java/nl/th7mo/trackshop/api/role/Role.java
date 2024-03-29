// XII·IX <> VII·X

package nl.th7mo.trackshop.api.role;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    private UUID id = UUID.randomUUID();
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
