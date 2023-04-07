package habiter.habiter.Config;

import habiter.habiter.Entity.User;
import habiter.habiter.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(repository.existsByEmail(email))
            return new UserPrincipal(repository.findByEmail(email));
        throw new UsernameNotFoundException("User not found with username or email : " + email);
    }
}