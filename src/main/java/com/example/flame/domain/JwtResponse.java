package com.example.flame.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class JwtResponse {
    private final String type = "Bearer ";
    private String accessToken;
    private String refreshToken;
}
