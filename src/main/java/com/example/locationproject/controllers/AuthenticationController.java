package com.example.locationproject.controllers;

import com.example.locationproject.dtos.securityDtos.LoginForm;
import com.example.locationproject.dtos.securityDtos.LoginResponse;
import com.example.locationproject.dtos.securityDtos.RegisterForum;
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

    @PutMapping("/change")
    public ResponseEntity<String> change(@RequestBody RegisterForum registerForum) {
        boolean changed = authService.change(registerForum);
        if (changed) {
            return ResponseEntity.ok("Password updated successfully to " + registerForum.password());
        } else {
            return ResponseEntity.status(400).body("Invalid username");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm) {
        try {
            return ResponseEntity.ok(authService.authenticate(loginForm));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body(new LoginResponse(e.getMessage(), null, 0L));
        }
    }
}