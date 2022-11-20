package com.example.flame.repository;

import com.example.flame.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getUserEntityByUsername(String username);

    @Query(value = "select * from tUser join user_roles ur on tUser.id = ur.user_id where ur.role_id = 1 and tUser.username =?1", nativeQuery = true)
    Optional<UserEntity> findAdminByLogin(String username);
}
