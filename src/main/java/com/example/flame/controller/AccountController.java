package com.example.flame.controller;

import com.example.flame.domain.JwtRequest;
import com.example.flame.domain.User;
import com.example.flame.entity.AccountEntity;
import com.example.flame.network.response.AccountResponse;
import com.example.flame.network.response.UserResponse;
import com.example.flame.service.AccountService;
import com.example.flame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/acc")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final AccountService accountService;

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserData(@RequestHeader("Authorization") String token) {

        var user = userService.getUser(token);

        if (Objects.equals(user.getErrorMessage(), null)) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body(user);
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/accounts")
    public ResponseEntity<ArrayList<AccountResponse>> getAccounts(@RequestHeader("Authorization") String token) {
        var accountEntities = accountService.getAllByUsername(token);

        ArrayList<AccountResponse> result = new ArrayList<>();

        if (!accountEntities.isEmpty()) {
            for (AccountEntity item : accountEntities) {
                AccountResponse ar = new AccountResponse(item.getNum(), item.getAmount());
                result.add(ar);
            }
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(null);
    }
}
