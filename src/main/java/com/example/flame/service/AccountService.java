package com.example.flame.service;

import com.example.flame.entity.AccountEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountService {
    Optional<ArrayList<AccountEntity>> getAllByUsername(String username);

    Optional<AccountEntity> getAllByUsernameAndCurrencyId(String username, long currencyId);

    void makeDeal(String username, long currencyId, double amount);
}
