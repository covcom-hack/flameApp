package com.example.flame.controller;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.User;
import com.example.flame.network.response.UserResponse;
import com.example.flame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acc")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserData(@RequestBody JwtRequest request) {

        var user =  userService.getByLogin(request.getLogin());

        if (user.isPresent()) {
            User u = user.get();
            UserResponse userResponse = new UserResponse(
                    u.surname(),
                    u.name(),
                    u.patronymic(),
                    u.passport(),
                    u.inn(),
                    u.phone(),
                    u.username()
            );
            return ResponseEntity.ok().body(userResponse);
        }

        return ResponseEntity.badRequest().body(null);
    }
}
