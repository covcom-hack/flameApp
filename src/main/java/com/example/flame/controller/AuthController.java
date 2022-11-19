package com.example.flame.controller;

import com.example.flame.domain.JwtRequest;
import com.example.flame.network.response.AuthResponse;
import com.example.flame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody JwtRequest jwtRequest) {
        var result = userService.login(jwtRequest);
        if (!Objects.equals(result.getErrorMessage(), "")) {
            return ResponseEntity.status(400).body(result);
        } else {
            return ResponseEntity.status(200).body(result);
        }
    }

//    @PostMapping("/admin/login")
//    public ResponseEntity loginAdmin(@RequestBody JwtRequest request) {
//
//    }

}
