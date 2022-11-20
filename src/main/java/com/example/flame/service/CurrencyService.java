package com.example.flame.service;

import com.example.flame.domain.Currency;
import com.example.flame.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    Currency addCurrency(Currency currency);

    List<Currency> getAllCurrencies();

    Optional<CurrencyEntity> getCurrencyByBrief(String brief);
}
