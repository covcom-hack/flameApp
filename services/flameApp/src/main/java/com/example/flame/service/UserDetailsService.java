package com.example.flame.service;

import com.example.flame.domain.JwtUserDetails;
import com.example.flame.entity.UserEntity;
import com.example.flame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<UserEntity> userEntity = userRepository.getUserEntityByUsername(username);
        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found!");
        }
        return JwtUserDetails.build(userEntity.get());
    }
}
