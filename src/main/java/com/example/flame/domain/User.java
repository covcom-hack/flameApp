package com.example.flame.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String firstName;
    private String surname;
    private String patronymic;
    private Integer passport;
    private String inn;
    private Integer phoneNumber;
    private String login;
    private Integer status;
    private String password;
    private Set<Role> roleSet;
}
