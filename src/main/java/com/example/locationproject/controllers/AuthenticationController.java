package com.example.locationproject.controllers;

import com.example.locationproject.dtos.securityDtos.LoginForm;
import com.example.locationproject.dtos.securityDtos.RegisterForum;

import com.example.locationproject.entities.AppUser;
import com.example.locationproject.services.securityServices.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterForum registerForum) {
        AppUser registered = authService.signUp(registerForum);
        if (registered != null) {
            return ResponseEntity.ok(registered.toString());
        } else {
            return ResponseEntity.status(400).body("Invalid username or password");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        try {
            return ResponseEntity.ok(authService.authenticate(loginForm));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}