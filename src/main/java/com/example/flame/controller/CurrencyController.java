package com.example.flame.controller;

import com.example.flame.domain.Currency;
import com.example.flame.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PreAuthorize("hasAuthority('User')")
    @PostMapping("add")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        var result = currencyService.addCurrency(currency);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("currencies")
    public ResponseEntity<List<Currency>> getCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }
}
