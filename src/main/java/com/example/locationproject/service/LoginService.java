package com.example.locationproject.service;

import com.example.locationproject.dto.LoginRequest;
import com.example.locationproject.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final Admin admin;

    public LoginService() {
        this.admin = new Admin();
    }

    public boolean login(LoginRequest loginRequest) {
        return admin.getUsername().equals(loginRequest.getUsername()) &&
                admin.getPassword().equals(loginRequest.getPassword());
    }
}
