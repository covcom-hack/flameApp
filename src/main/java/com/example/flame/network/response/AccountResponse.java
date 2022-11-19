package com.example.flame.network.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AccountResponse {
    private String num;
    private Double amount;
}
