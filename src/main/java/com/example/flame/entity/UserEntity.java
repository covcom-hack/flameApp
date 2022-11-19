package com.example.flame.entity;

import com.example.flame.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ), inverseJoinColumns = @JoinColumn(
            name = "role_id",
            referencedColumnName = "roleid"
    ))
    private Set<UserRoleEntity> roles;


}
