package com.example.flame.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "passport")
    private Integer passport;

    @Column(name = "inn")
    private String inn;

    @Column(name = "phonenumber")
    private Integer phoneNumber;

    @Column(name = "login")
    private String login;

    @Column(name = "status")
    private Integer status;

    @Column(name = "password")
    private String password;


}
