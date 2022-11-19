package com.example.flame.network.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserResponse {
    private String surname;
    private String name;
    private String patronymic;
    private String passport;
    private String inn;
    private String phone;
    private String username;
}
