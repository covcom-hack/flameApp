package com.example.flame.service.impl;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.JwtResponse;
import com.example.flame.service.AuthService;
import com.example.flame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    @Override
    public JwtResponse login(JwtRequest request) {
        return null;
    }
}
