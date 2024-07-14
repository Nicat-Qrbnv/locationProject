package com.example.locationproject.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


@Getter
public enum Role implements GrantedAuthority {
    ROLE_ADMIN ("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
