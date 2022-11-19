package com.example.flame.service;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.JwtResponse;
import com.example.flame.network.response.AuthResponse;

public interface AuthService {
    AuthResponse login(JwtRequest request);
}
