package com.example.flame.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class JwtResponse {
    private final String type = "Bearer ";
    private String accessToken;
    private String refreshToken;
}
