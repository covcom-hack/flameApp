package com.example.flame.controller;

import com.example.flame.entity.CurrencyEntity;
import com.example.flame.entity.DealEntity;
import com.example.flame.network.response.DealHistoryResponse;
import com.example.flame.service.AccountService;
import com.example.flame.service.CurrencyService;
import com.example.flame.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/deal")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;
    private final AccountService accountService;
    private final CurrencyService currencyService;

    // Возвращает историю операций по переданному биржевому счету
    @GetMapping("/getDealHistory")
    public ResponseEntity<ArrayList<DealHistoryResponse>> getDealHistory(@RequestBody String username, String num,
                                                              LocalDateTime from, LocalDateTime to) {
        var history = dealService.getHistory(from, to, username, num);

        if (history.isPresent()) {
            ArrayList<DealHistoryResponse> result = new ArrayList<>();
            ArrayList<DealEntity> deals = history.get();
            for (DealEntity item : deals) {

                String numFrom = item.getNumFrom();
                String numTo = item.getNumTo();
                String direction = "";

                if (numFrom.equals(num) && numTo.equals("брокер")) direction = "списание";
                else if (numFrom.equals(num) && numTo.equals("карта")) direction = "вывод средств";
                else if (numFrom.equals("брокер") && numTo.equals(num)) direction = "поступление";
                else if (numFrom.equals("карта") && numTo.equals(num)) direction = "пополнение c карты";

                DealHistoryResponse dhr = new DealHistoryResponse(num, item.getAmount(), item.getDateTime(), direction);
                result.add(dhr);
            }
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(null);
    }

    public ResponseEntity<Boolean> makeDeal(@RequestBody String username, String briefFrom,
                                            String briefTo, Double amountFrom, Double amountTo) {
        //1. Получить accNum по username и brief
        Optional<CurrencyEntity> cf = currencyService.getCurrencyByBrief(briefFrom);
        Optional<CurrencyEntity> ct = currencyService.getCurrencyByBrief(briefTo);
        if (cf.isEmpty() || ct.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }

        long idF = cf.get().getId();
        long idT = ct.get().getId();

        accountService.makeDeal(username, idF, amountFrom * (-1));
        accountService.makeDeal(username, idT, amountTo);

        return ResponseEntity.ok(true);
    }
}
