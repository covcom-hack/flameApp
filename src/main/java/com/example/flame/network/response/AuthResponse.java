package com.example.flame.network.response;

import com.example.flame.domain.JwtResponse;
import lombok.*;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AuthResponse {
    private JwtResponse jwtResponse;
    private String errorMessage;
    private String role;
}
