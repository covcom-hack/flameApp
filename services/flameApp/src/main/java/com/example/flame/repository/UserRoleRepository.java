package com.example.flame.repository;

import com.example.flame.domain.Role;
import com.example.flame.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(Role role);
}
