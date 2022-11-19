package com.example.flame.repository;

import com.example.flame.entity.RefreshTokenEntity;
import com.example.flame.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Long deleteRefreshTokenEntityByUserEntity(UserEntity user);

    RefreshTokenEntity findRefreshTokenEntitiesByRefreshToken(String refreshToken);
}
