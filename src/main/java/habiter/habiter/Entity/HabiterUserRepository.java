package habiter.habiter.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabiterUserRepository extends JpaRepository<HabiterUser, Long> {
    Optional<HabiterUser> findByEmail(String email);
}
