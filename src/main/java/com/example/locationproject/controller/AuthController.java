package com.example.locationproject.controller;

//import com.example.locationproject.dto.LoginRequest;
//import com.example.locationproject.service.LoginService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final LoginService loginService;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        if (loginService.login(loginRequest)) {
//            return ResponseEntity.ok("Login successful. Redirecting to admin dashboard...");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
//        }
//    }
//}
