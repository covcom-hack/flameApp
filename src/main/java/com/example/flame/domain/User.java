package com.example.flame.domain;

import lombok.Builder;

@Builder
public record User(String firstName, String surName, String patronymic, Integer passport, String inn,
                   Integer phoneNumber, String login, Integer status, String password) {
}
