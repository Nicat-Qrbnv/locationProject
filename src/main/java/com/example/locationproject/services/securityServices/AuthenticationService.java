package com.example.locationproject.services.securityServices;

import com.example.locationproject.dtos.securityDtos.LoginForm;
import com.example.locationproject.dtos.securityDtos.LoginResponse;
import com.example.locationproject.dtos.securityDtos.RegisterForum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final PasswordEncoder passEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userService;
    private final InMemoryUserDetailsManager inMemUserDetManager;

    public boolean change(RegisterForum dto) {
        UserDetails userDetails = userService.loadUserByUsername(dto.username());
        String encoded = passEncoder.encode(dto.password());
        if (userDetails != null && userDetails.getPassword().equals(encoded)) {
            inMemUserDetManager.updatePassword(userDetails, encoded);
            return true;
        } else {
            return false;
        }
    }

    public LoginResponse authenticate(LoginForm form) throws UsernameNotFoundException {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password()));
        if (auth.isAuthenticated()) {
            String token = jwtService.generateToken(userService.loadUserByUsername(form.username()));
            long expiresIn = jwtService.getExpirationTime(token);
            return new LoginResponse("success", token, expiresIn);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}