package com.example.locationproject.services.securityServices;

import com.example.locationproject.entities.AppUser;
import com.example.locationproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> found = userRepo.findByUsername(username);
        if (found.isPresent()) {
            AppUser appUser = found.get();
            return User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .authorities(appUser.getAuthorities())
                    .build();
        } else {
            throw new UsernameNotFoundException("User with \"%s\" username not found: ".formatted(username));
        }
    }
}
