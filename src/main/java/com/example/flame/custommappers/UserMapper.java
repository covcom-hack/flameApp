package com.example.flame.custommappers;

import com.example.flame.domain.User;
import com.example.flame.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(user.password());
        userEntity.setInn(user.inn());
        userEntity.setLogin(user.inn());
        userEntity.setPhoneNumber(user.phoneNumber());
        userEntity.setStatus(user.status());
        userEntity.setName(user.firstName() + " " + user.surName() + " " + user.patronymic());
        return userEntity;
    }
}
