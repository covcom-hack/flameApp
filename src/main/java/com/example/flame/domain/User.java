package com.example.flame.domain;

import lombok.Builder;
import lombok.Getter;

@Builder

public record User(Long id, String firstName, String surName, String patronymic, Integer passport, String inn,
                   Integer phoneNumber, String login, Integer status) {
}
