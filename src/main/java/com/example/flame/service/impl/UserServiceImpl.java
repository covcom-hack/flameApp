package com.example.flame.service.impl;

import com.example.flame.custommappers.UserMapper;
import com.example.flame.domain.*;
import com.example.flame.entity.RefreshTokenEntity;
import com.example.flame.entity.UserEntity;
import com.example.flame.entity.UserRoleEntity;
import com.example.flame.network.response.AuthResponse;
import com.example.flame.repository.RefreshTokenRepository;
import com.example.flame.repository.UserRepository;
import com.example.flame.repository.UserRoleRepository;
import com.example.flame.service.JwtProvider;
import com.example.flame.service.RefreshTokenService;
import com.example.flame.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;


    @Override
    public Optional<User> getByLogin(@NonNull String login) {
        var result = userRepository.getUserEntityByUsername(login);
        if (result.isPresent()) {
            var user = modelMapper.map(result.get(), User.class);
            return Optional.of(user);
        } else
            return Optional.empty();
    }

    @Override
    public User addUser(User user) {
        var entity = modelMapper.map(user, UserEntity.class);
        var savedEntity = userRepository.save(entity);
        return modelMapper.map(savedEntity, User.class);
    }

    @Override
    public User register(User user) {
        var userEntity = userMapper.mapToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        Set<UserRoleEntity> roles = userEntity.getRoles().stream().map(
                role -> userRoleRepository.findByRole(role.getRole())
        ).collect(Collectors.toSet());
        userEntity.setRoles(roles);
        var savedEntity = userRepository.save(userEntity);
        return modelMapper.map(savedEntity, User.class);
    }

    @Override
    public AuthResponse login(JwtRequest request) {
        AuthResponse authResponse = new AuthResponse();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPass())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        String accessToken = jwtProvider.createToken(userDetails);

        Optional<String> refreshTokenOptional = refreshTokenService.updateRefreshToken(userDetails.getId());
        if (refreshTokenOptional.isEmpty()) {
            authResponse.setErrorMessage("Такого пользователя не существует");
            return authResponse;
        }
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(accessToken);
        jwtResponse.setRefreshToken(refreshTokenOptional.get());
        authResponse.setJwtResponse(jwtResponse);
        authResponse.setRole(roles.get(0));
        return authResponse;
    }

    @Override
    public AuthResponse loginAdmin(JwtRequest request) {
        return null;
    }

}
