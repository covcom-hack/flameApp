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
    private String name;
    private String surname;
    private String patronymic;
    private String passport;
    private String inn;
    private String phone;
    private String username;
    private Integer status;
    private String password;
    private Set<Role> roleSet;
}
