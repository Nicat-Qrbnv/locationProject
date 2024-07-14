package com.example.locationproject.dtos.securityDtos;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}
