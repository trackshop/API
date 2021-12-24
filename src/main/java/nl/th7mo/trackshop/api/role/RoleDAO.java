// XII·IX <> VII·X

package nl.th7mo.trackshop.api.role;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleDAO {

    private final RoleRepository roleRepository;

    public void post(Role role) {
        roleRepository.save(role);
    }

    public Role getByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
