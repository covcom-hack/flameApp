package com.example.flame.custommappers;

import com.example.flame.domain.Currency;
import com.example.flame.entity.CurrencyEntity;
import org.springframework.stereotype.Component;


@Component
public class CurrencyMapper {
    public CurrencyEntity currencyToEntity(Currency currency) {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setBrief(currency.getBrief());
        return currencyEntity;
    }

    public Currency entityToCurrency(CurrencyEntity entity) {
        Currency currency = new Currency();
        currency.setBrief(entity.getBrief());
        return currency;
    }
}
