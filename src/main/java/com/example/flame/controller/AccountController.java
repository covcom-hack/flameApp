package com.example.flame.controller;

import com.example.flame.domain.JwtRequest;
import com.example.flame.service.UserService;
import com.example.flame.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acc")
@RequiredArgsConstructor
public class AccountController {

    UserService us;

    @GetMapping("/resp")
    public ResponseEntity<String> getUserData(JwtRequest request) {


        return new ResponseEntity<String>("Response", HttpStatus.OK);
    }
}
