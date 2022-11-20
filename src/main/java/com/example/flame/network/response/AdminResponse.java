package com.example.flame.network.response;

import com.example.flame.domain.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AdminResponse {
    private User admin;
    private String errorMessage;
}
