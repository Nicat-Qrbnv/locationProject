package com.example.locationproject.dtos.securityDtos;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String fullName;
    private String email;
    private String password;
}
