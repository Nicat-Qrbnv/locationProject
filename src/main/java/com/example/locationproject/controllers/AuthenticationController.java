package com.example.locationproject.controllers;

import com.example.locationproject.dtos.securityDtos.LoginResponse;
import com.example.locationproject.dtos.securityDtos.LoginUserDto;
import com.example.locationproject.dtos.securityDtos.RegisterUserDto;

import com.example.locationproject.entities.User;
import com.example.locationproject.services.securityServices.AuthenticationService;
import com.example.locationproject.services.securityServices.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody RegisterUserDto registerUserDto) {
        log.info("RegisterUserDto: {}", registerUserDto);
        User registered = authService.signUp(registerUserDto);
        log.info("RegisterUserDto: {}", registered);
        return registered;
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginUserDto loginUserDto) {
        log.info("LoginUserDto: {}", loginUserDto);
        User authenticated = authService.authenticate(loginUserDto);
        log.info("LoginUserDto: {}", authenticated);
        String jwtToken = jwtService.generateToken(new HashMap<>(), authenticated);
        log.info("LoginUserDto: {}", jwtToken);
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        log.info("LoginResponse: {}", loginResponse);
        return loginResponse;
    }
}
