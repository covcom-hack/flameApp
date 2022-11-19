package com.example.flame.service.impl;

import com.example.flame.custommappers.UserMapper;
import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.JwtResponse;
import com.example.flame.domain.Role;
import com.example.flame.domain.User;
import com.example.flame.entity.RefreshTokenEntity;
import com.example.flame.entity.UserEntity;
import com.example.flame.entity.UserRoleEntity;
import com.example.flame.network.response.AuthResponse;
import com.example.flame.repository.RefreshTokenRepository;
import com.example.flame.repository.UserRepository;
import com.example.flame.repository.UserRoleRepository;
import com.example.flame.service.JwtProvider;
import com.example.flame.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> getByLogin(@NonNull String username) {
        var result = userRepository.getUserEntityByUsername(username);
        if (result.isPresent()) {
            var user = modelMapper.map(userRepository.getUserEntityByUsername(username), User.class);
            return Optional.of(user);
        } else
            return Optional.empty();
    }

    @Override
    public User addUser(User user) {
        var entity = modelMapper.map(user, UserEntity.class);
        var savedEntity = userRepository.save(entity);
        return modelMapper.map(savedEntity, User.class);
    }

    @Override
    public User register(User user) {
        var userEntity = userMapper.mapToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        Set<UserRoleEntity> roles = userEntity.getRoles().stream().map(
                role -> userRoleRepository.findByRole(role.getRole())
        ).collect(Collectors.toSet());
        userEntity.setRoles(roles);
        var savedEntity = userRepository.save(userEntity);
        return modelMapper.map(savedEntity, User.class);
    }

    @Override
    public AuthResponse login(JwtRequest request) {
        AuthResponse authResponse = new AuthResponse();
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        final var user = getByLogin(request.getLogin());
        if (user.isEmpty()) {
            authResponse.setErrorMessage("Пользователь с таким логином: " + request.getLogin() + " не найден!");
            return authResponse;
        }
        var userDto = user.get();
        refreshTokenEntity.setUserEntity(modelMapper.map(userDto, UserEntity.class));
        if (Objects.equals(userDto.getPassword(), request.getPass())) {
            final String accessToken = jwtProvider.generateAccessToken(userDto);
            final String refreshToken = jwtProvider.generateRefreshToken(userDto);
            refreshTokenEntity.setRefreshToken(refreshToken);
            refreshTokenRepository.save(refreshTokenEntity);
            JwtResponse jwtResponse = JwtResponse.builder().refreshToken(refreshToken).accessToken(accessToken).build();
            authResponse.setJwtResponse(jwtResponse);
        } else {
            authResponse.setErrorMessage("Неправильный пароль!");
        }
        return authResponse;
    }

}
