package com.example.locationproject.dtos.securityDtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}