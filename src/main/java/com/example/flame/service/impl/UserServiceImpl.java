package com.example.flame.service.impl;

import com.example.flame.domain.User;
import com.example.flame.entity.UserEntity;
import com.example.flame.repository.UserRepository;
import com.example.flame.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getByLogin(@NonNull String login) {
        return modelMapper.map(userRepository.getUserEntityByLogin(login), User.class);
    }

    @Override
    public User addUser(User user) {
        var entity = modelMapper.map(user, UserEntity.class);
        var savedEntity = userRepository.save(entity);
        return modelMapper.map(savedEntity, User.class);
    }

    @Override
    public User register(User user) {
        var userEntity = modelMapper.map(user, UserEntity.class);
        return null;
    }
}
