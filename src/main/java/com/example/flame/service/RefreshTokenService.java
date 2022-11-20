package com.example.flame.service;

import com.example.flame.entity.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<String> create(Long userId);

    Long deleteByUserId(Long userId);

    Optional<String> updateRefreshToken(Long userId);

    Optional<RefreshTokenEntity> findByRefreshTokenString(String refreshTokenString);
}
