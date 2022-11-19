package com.example.flame.custommappers;

import com.example.flame.domain.Role;
import com.example.flame.domain.User;
import com.example.flame.entity.UserEntity;
import com.example.flame.entity.UserRoleEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(user.getPassword());
        userEntity.setInn(user.getInn());
        userEntity.setLogin(user.getLogin());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setStatus(user.getStatus());
        userEntity.setName(user.getFirstName() + " " + user.getSurname() + " " + user.getPatronymic());
        userEntity.setRoles(user.getRoleSet().stream().map(UserRoleEntity::new).collect(Collectors.toSet()));
        return userEntity;
    }
}
