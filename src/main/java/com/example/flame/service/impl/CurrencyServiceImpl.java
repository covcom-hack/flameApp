package com.example.flame.service.impl;

import com.example.flame.custommappers.CurrencyMapper;
import com.example.flame.domain.Currency;
import com.example.flame.entity.CurrencyEntity;
import com.example.flame.repository.CurrencyRepository;
import com.example.flame.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper modelMapper;

    @Override
    public Currency addCurrency(Currency currency) {
        var entity = modelMapper.currencyToEntity(currency);
        var savedEntity = currencyRepository.save(entity);
        return modelMapper.entityToCurrency(savedEntity);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll().stream().map(modelMapper::entityToCurrency).toList();
    }
}
