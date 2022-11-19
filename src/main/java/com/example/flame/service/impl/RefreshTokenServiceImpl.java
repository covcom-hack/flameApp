package com.example.flame.service.impl;

import com.example.flame.entity.RefreshTokenEntity;
import com.example.flame.entity.UserEntity;
import com.example.flame.repository.RefreshTokenRepository;
import com.example.flame.repository.UserRepository;
import com.example.flame.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<String> create(Long userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent())
            refreshToken.setUserEntity(userOptional.get());
        else
            return Optional.empty();
        String tokenData = UUID.randomUUID().toString();
        refreshToken.setRefreshToken(tokenData);

        refreshTokenRepository.save(refreshToken);
        return Optional.of(tokenData);
    }

    @Override
    public Long deleteByUserId(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
//        return refreshTokenRepository.deleteRefreshTokenEntityByUserEntity(userOptional.get());
        return userOptional.map(refreshTokenRepository::deleteByUserEntity).orElse(0L);
    }

    @Override
    public Optional<String> updateRefreshToken(Long userId) {
        this.deleteByUserId(userId);
        return this.create(userId);
    }

    @Override
    public Optional<RefreshTokenEntity> findByRefreshTokenString(String refreshTokenString) {
        RefreshTokenEntity token = refreshTokenRepository.findRefreshTokenEntitiesByRefreshToken(refreshTokenString);
        if (token == null)
            return Optional.empty();
        return Optional.of(token);
    }
}
