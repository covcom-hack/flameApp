package com.example.flame.network.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class DealHistoryResponse {
    private String num;
    private Double amount;
    private LocalDateTime dateTime;
    private String direction;

}
