package com.example.flame.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("Admin"),
    USER("User");

    private final String val;

    @Override
    public String getAuthority() {
        return val;
    }
}
