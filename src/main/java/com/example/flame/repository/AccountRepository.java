package com.example.flame.repository;

import com.example.flame.entity.AccountEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountRepository {
    Optional<ArrayList<AccountEntity>> getAllByUsername(String username);
}
