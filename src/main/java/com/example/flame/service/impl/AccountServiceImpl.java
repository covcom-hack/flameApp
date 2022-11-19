package com.example.flame.service.impl;

import com.example.flame.entity.AccountEntity;
import com.example.flame.repository.AccountRepository;
import com.example.flame.service.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Optional<ArrayList<AccountEntity>> getAllByUsername(@NonNull String username) {
        return accountRepository.getAllByUsername(username);
    }
}
