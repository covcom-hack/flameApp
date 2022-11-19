package com.example.flame.service.impl;

import com.example.flame.domain.Role;
import com.example.flame.domain.User;
import com.example.flame.entity.UserEntity;
import com.example.flame.entity.UserRoleEntity;
import com.example.flame.repository.UserRepository;
import com.example.flame.repository.UserRoleRepository;
import com.example.flame.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

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
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        Set<UserRoleEntity> roles = userEntity.getRoles().stream().map(
                role -> userRoleRepository.findByRole(role.getRole())
        ).collect(Collectors.toSet());
        userEntity.setRoles(roles);
        var savedEntity = userRepository.save(userEntity);
        return modelMapper.map(savedEntity, User.class);
    }
}
