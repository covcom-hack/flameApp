package com.example.flame.domain;

import lombok.Builder;

@Builder
public record User(String name, String surname, String patronymic, String passport,
                   String inn, String phone, Integer status, String username,
                   String password) {
}
