package habiter.habiter.Services;

import habiter.habiter.Entity.Role;
import habiter.habiter.Entity.User;
import habiter.habiter.Enums.RoleName;
import habiter.habiter.Exeptions.AppException;
import habiter.habiter.Repositories.RoleRepository;
import habiter.habiter.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    public User save(User user) {
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email.trim().toLowerCase());
    }

    @Transactional
    public User create(User user) {
        // Creating user's account
        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }
}
