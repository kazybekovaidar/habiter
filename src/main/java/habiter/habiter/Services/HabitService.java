package habiter.habiter.Services;

import habiter.habiter.Entity.StandardHabitCard;
import habiter.habiter.Entity.User;
import habiter.habiter.Models.Payloads.HabitModel;
import habiter.habiter.Repositories.StandardHabitCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final UserService userService;
    private final StandardHabitCardRepository standardHabitCardRepository;
    public StandardHabitCard bindHabitModelToUser(Long userId, HabitModel habitModel) {
        // Retrieve the authenticated user using the token
        User user = null;
        try {
            user = userService.findUserById(userId);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token");
        }
        //   Bind the habitModel to the user and save it
        StandardHabitCard habit = new StandardHabitCard();
        habit.setDescription(habitModel.getDescription());
        habit.setGoal(habitModel.getGoal());
        habit.setUnit(habitModel.getUnit());
        habit.setName(habitModel.getName());
        habit.setUser(user);
        habit.setStartDate(habitModel.getStartDate());
        habit.setEndDate(habitModel.getEndDate());
        //  habitService.create(habitModel, user);
        StandardHabitCard habitCard = standardHabitCardRepository.save(habit);
        return habitCard;
    }

    public List<StandardHabitCard> findAllByUser(Long userId) {
        User user = null;
        try {
            user = userService.findUserById(userId);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token");
        }
        List<StandardHabitCard> habits = standardHabitCardRepository.findAllByUser(user);
        return habits;
    }

    public void deleteHabit(Long userId, Long habitId) {
        User user = null;
        try {
            user = userService.findUserById(userId);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token");
        }
        // find the habit by habitId and user
        StandardHabitCard habit = standardHabitCardRepository.findStandardHabitCardByUserAndId(user, habitId);
        // delete the habit
        standardHabitCardRepository.delete(habit);
    }
}
