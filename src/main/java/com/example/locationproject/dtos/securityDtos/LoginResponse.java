package com.example.locationproject.dtos.securityDtos;

public record LoginResponse (String token, long expiresIn) {}