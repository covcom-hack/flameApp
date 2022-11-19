package com.example.flame.custommappers;

import com.example.flame.domain.User;
import com.example.flame.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(user.getPassword());
        userEntity.setInn(user.getInn());
        userEntity.setUsername(user.getUsername());
        userEntity.setPhone(user.getPhone());
        userEntity.setStatus(user.getStatus());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setPatronymic(user.getPatronymic());
        userEntity.setPassport(user.getPassport());
        return userEntity;
    }
}
