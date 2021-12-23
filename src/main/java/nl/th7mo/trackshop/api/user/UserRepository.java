// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {

    AppUser findByEmailAddress(String emailAddress);
}
