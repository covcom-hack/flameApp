package com.example.flame.custommappers;

import com.example.flame.domain.User;
import com.example.flame.entity.UserEntity;
import com.example.flame.entity.UserRoleEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(user.getPassword());
        userEntity.setInn(user.getInn());
        userEntity.setUsername(user.getUsername());
        userEntity.setPhone(user.getPhone());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setPatronymic(user.getPatronymic());
        userEntity.setPassport(user.getPassport());
        userEntity.setRoles(user.getRoleSet().stream().map(UserRoleEntity::new).collect(Collectors.toSet()));
        return userEntity;
    }

//    public User mapToDto(UserEntity user){
//        User user1
//    }
}
