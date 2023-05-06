package habiter.habiter.Controller;

import habiter.habiter.Config.JwtService;
import habiter.habiter.Entity.Role;
import habiter.habiter.Entity.User;
import habiter.habiter.Enums.RoleName;
import habiter.habiter.Models.Payloads.ApiResponse;
import habiter.habiter.Models.Payloads.SigninModel;
import habiter.habiter.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @GetMapping
    public String getHelloWorld(){
        return "hello world";
    }

    @GetMapping("/second")
    public String getSecond() {
        return "second hello world";
    }

    @PostMapping("/singup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpUser) {
        User checkUser = userService.findByEmail(signUpUser.getEmail());
        if(checkUser != null) {
            return new ResponseEntity(new ApiResponse(false, "Email is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        User savedUser = userService.create(signUpUser);

        return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/signin")
    public Map<String, Object> authenticateAndGetToken(@RequestBody SigninModel authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        Map<String, Object> map = new HashMap<>();
        User user = null;
        if (authentication.isAuthenticated()) {
            map.put("token",jwtService.generateToken(authRequest.getEmail()));
            user = userService.findByEmail(authRequest.getEmail());
            map.put("fullName", user.getFirstname() + " " + user.getLastname());
            map.put("email", user.getEmail());
            map.put("id", user.getId());
            return map;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}