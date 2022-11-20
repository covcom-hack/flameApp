package com.example.flame.entity;

import com.example.flame.domain.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "`tRoles`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Long roleId;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRoleEntity(Role role) {
        this.role = role;
    }
}
