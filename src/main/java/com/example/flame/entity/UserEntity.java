package com.example.flame.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@RequiredArgsConstructor
@Getter
public class UserEntity {
    private final Long id;
    private final String name;
    private final Integer passport;
    private final String inn;
    private final Integer phoneNumber;
    private final String login;
    private final Integer status;
}
