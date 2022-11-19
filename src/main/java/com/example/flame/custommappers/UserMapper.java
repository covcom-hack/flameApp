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
        userEntity.setUsername(user.username());
        userEntity.setPhone(user.phone());
        userEntity.setStatus(user.status());
        userEntity.setName(user.name());
        userEntity.setSurname(user.surname());
        userEntity.setPatronymic(user.patronymic());
        userEntity.setPassport(user.passport());
        return userEntity;
    }
}
