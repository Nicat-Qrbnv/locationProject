package com.example.locationproject.dtos.securityDtos;

public record LoginResponse (String result, String token, long expiresIn) {}