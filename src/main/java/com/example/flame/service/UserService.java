package com.example.flame.service;

import com.example.flame.domain.User;
import lombok.NonNull;

public interface UserService {
    User getByLogin(@NonNull String login);

    User addUser(User user);

    User register(User user);
}
