package com.example.flame.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tTokens")
@Data
public class RefreshTokenEntity {
    @Id
    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private UserEntity userEntity;
}
