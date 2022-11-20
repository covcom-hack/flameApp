package com.example.flame.controller;

import com.example.flame.domain.User;
import com.example.flame.entity.AccountEntity;
import com.example.flame.network.response.AccountResponse;
import com.example.flame.network.response.UserResponse;
import com.example.flame.service.AccountService;
import com.example.flame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/acc")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final AccountService accountService;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserData(@RequestBody String username) {

        var user =  userService.getByLogin(username);

        if (user.isPresent()) {
            User u = user.get();
            UserResponse userResponse = new UserResponse(
                    u.getSurname(),
                    u.getName(),
                    u.getPatronymic(),
                    u.getPassport(),
                    u.getInn(),
                    u.getPhone(),
                    u.getUsername()
            );
            return ResponseEntity.ok().body(userResponse);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/accounts")
    public ResponseEntity<ArrayList<AccountResponse>> getAccounts(@RequestBody String username) {
        var optional = accountService.getAllByUsername(username);

        ArrayList<AccountResponse> result = new ArrayList<>();

        if (optional.isPresent()) {
            ArrayList<AccountEntity> accounts = optional.get();
            for (AccountEntity item : accounts) {
                AccountResponse ar = new AccountResponse(item.getNum(), item.getAmount());
                result.add(ar);
            }
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(null);
    }
}
