package habiter.habiter.Repositories;

import habiter.habiter.Entity.Role;
import habiter.habiter.Entity.User;
import habiter.habiter.Enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
