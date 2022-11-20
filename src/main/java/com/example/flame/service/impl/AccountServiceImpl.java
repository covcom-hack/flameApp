package com.example.flame.service.impl;

import com.example.flame.domain.JwtUserDetails;
import com.example.flame.entity.AccountEntity;
import com.example.flame.repository.AccountRepository;
import com.example.flame.service.AccountService;
import com.example.flame.service.JwtProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final JwtProvider jwtProvider;

    @Override
    public ArrayList<AccountEntity> getAllByUsername(@NonNull String token) {
        var authentication = jwtProvider.getAuth(token);
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();
        return accountRepository.getAllByUsername(username);
    }

    @Override
    public Optional<AccountEntity> getAllByUsernameAndCurrencyId(String username, long currencyId) {
        return accountRepository.getAllByUsernameAndCurrencyId(username, currencyId);
    }

    @Override
    public void makeDeal(String username, long currencyId, double amount) {
        accountRepository.makeDeal(username, currencyId, amount);
    }
}
