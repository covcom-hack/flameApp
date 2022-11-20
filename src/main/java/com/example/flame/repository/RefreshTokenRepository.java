package com.example.flame.repository;

import com.example.flame.entity.RefreshTokenEntity;
import com.example.flame.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    @Transactional
    Long deleteByUserEntity(UserEntity user);

    RefreshTokenEntity findRefreshTokenEntitiesByRefreshToken(String refreshToken);
}
