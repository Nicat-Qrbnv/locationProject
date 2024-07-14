package com.example.locationproject.services.securityServices;

import com.example.locationproject.dtos.securityDtos.LoginUserDto;
import com.example.locationproject.dtos.securityDtos.RegisterUserDto;
import com.example.locationproject.entities.User;
import com.example.locationproject.enums.Role;
import com.example.locationproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepo;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passEncoder;
    private final ModelMapper mapper;

    public User signUp (RegisterUserDto dto) {
        User user = new User(null, dto.getFullName(),
                dto.getEmail(), passEncoder.encode(dto.getPassword()));
        user.addAuthority(Role.ROLE_ADMIN);
        return userRepo.save(user);
    }

    public User authenticate(LoginUserDto dto) {
        log.info("trying to login");
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("end of authentication");
        User user = userRepo.findByUsername(dto.getEmail()).orElseThrow();
        log.info("User: {}", user);
        return user;
    }
}