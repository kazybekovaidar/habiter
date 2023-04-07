package habiter.habiter.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habit")
public class HabitController {
    @GetMapping("/greeting")
    public String getSecond() {
        return "greeting from habitcontroller";
    }
}
