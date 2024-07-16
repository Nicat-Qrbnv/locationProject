package com.example.locationproject.services.securityServices;

import com.example.locationproject.dtos.securityDtos.LoginForm;
import com.example.locationproject.dtos.securityDtos.RegisterForum;
import com.example.locationproject.entities.AppUser;
import com.example.locationproject.enums.Role;
import com.example.locationproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final PasswordEncoder passEncoder;
    private final UserRepository userRepo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;


    public AppUser signUp(RegisterForum dto) {
        Optional<AppUser> found = userRepo.findByUsername(dto.username());
        if (found.isEmpty()) {
            AppUser user = new AppUser(null, dto.username(), passEncoder.encode(dto.password()), true);
            user.addAuthority(Role.ROLE_ADMIN);
            return userRepo.save(user);
        }
        return found.get();
    }

    public String authenticate(LoginForm form) throws UsernameNotFoundException {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password()));
        if (auth.isAuthenticated()) {
            return jwtService.generateToken(userService.loadUserByUsername(form.username()));
        } else {
            throw new UsernameNotFoundException("User with \"%s\" username not found: ".formatted(form.username()));
        }
    }
}