package com.example.flame.service.impl;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.JwtResponse;
import com.example.flame.domain.User;
import com.example.flame.entity.RefreshTokenEntity;
import com.example.flame.entity.UserEntity;
import com.example.flame.network.response.AuthResponse;
import com.example.flame.repository.RefreshTokenRepository;
import com.example.flame.service.AuthService;
import com.example.flame.service.JwtProvider;
import com.example.flame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.table.AbstractTableModel;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthResponse login(JwtRequest request) {
        AuthResponse authResponse = new AuthResponse();
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        final var user = userService.getByLogin(request.getLogin());
        if (user == null) {
            authResponse.setErrorMessage("Пользователь с таким логином: " + request.getLogin() + " не найден!");
            return authResponse;
        }
        refreshTokenEntity.setUserEntity(modelMapper.map(user, UserEntity.class));
        if (Objects.equals(user.password(), request.getPass())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
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
