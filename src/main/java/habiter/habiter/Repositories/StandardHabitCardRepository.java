package habiter.habiter.Repositories;

import habiter.habiter.Entity.StandardHabitCard;
import habiter.habiter.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardHabitCardRepository extends JpaRepository<StandardHabitCard, Long> {
    StandardHabitCard save(StandardHabitCard standardHabitCard);
    List<StandardHabitCard> findAllByUser(User user);
    StandardHabitCard findStandardHabitCardByUserAndId(User user, Long userId);
}
