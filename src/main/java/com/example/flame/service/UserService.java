package com.example.flame.service;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.User;
import com.example.flame.network.response.AuthResponse;
import com.example.flame.network.response.UserResponse;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {
    UserResponse getUser(String token);

    User addUser(User user);

    User register(User user);

    AuthResponse login(JwtRequest request);

}
