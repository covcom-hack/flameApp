package com.example.flame.service;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest request);
}
