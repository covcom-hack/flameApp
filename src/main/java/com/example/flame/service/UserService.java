package com.example.flame.service;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.User;
import com.example.flame.network.response.AuthResponse;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {
    Optional<User> getByLogin(@NonNull String login);

    User addUser(User user);

    User register(User user);

    AuthResponse login(JwtRequest request);

    AuthResponse loginAdmin(JwtRequest request);
}
