package habiter.habiter.Controller;

import habiter.habiter.Config.JwtService;
import habiter.habiter.Entity.StandardHabitCard;
import habiter.habiter.Entity.User;
import habiter.habiter.Models.Payloads.HabitModel;
import habiter.habiter.Repositories.StandardHabitCardRepository;
import habiter.habiter.Services.HabitService;
import habiter.habiter.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/habit")
public class HabitController {
    private final StandardHabitCardRepository standardHabitCardRepository;
    private final HabitService habitService;
    private final UserService userService;

    @GetMapping("/greeting")
    public String getSecond() {
        return "greeting from habitcontroller";
    }
    @PostMapping("/{userId}")
    public ResponseEntity<?> createHabit(@PathVariable Long userId,
                                         @Valid @RequestBody HabitModel habitModel) {
        StandardHabitCard habitCard = habitService.bindHabitModelToUser(userId, habitModel);
        return ResponseEntity.ok(habitCard);
    }
    @GetMapping("/getHabit/{userId}")
    public ResponseEntity<?> getHabits(@PathVariable Long userId){
        List<StandardHabitCard> habits = habitService.findAllByUser(userId);
        return ResponseEntity.ok(habits);
    }
    @DeleteMapping("deleteHabit/{userId}/{habitId}")
    public ResponseEntity<?> deleteHabit(@PathVariable Long userId, @PathVariable Long habitId){
        habitService.deleteHabit(userId, habitId);
        return ResponseEntity.ok().build();
    }


}