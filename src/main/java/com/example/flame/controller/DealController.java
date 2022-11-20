package com.example.flame.controller;

import com.example.flame.domain.JwtRequest;
import com.example.flame.entity.DealEntity;
import com.example.flame.network.response.DealHistoryResponse;
import com.example.flame.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("api/deal")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping("/getDealHistory")
    public ResponseEntity<ArrayList<DealHistoryResponse>> getDealHistory(@RequestBody JwtRequest jwtRequest, String num,
                                                              LocalDateTime from, LocalDateTime to) {
        String username = jwtRequest.getLogin();

        var history = dealService.getHistory(from, to, username, num);

        if (history.isPresent()) {
            ArrayList<DealHistoryResponse> result = new ArrayList<>();
            ArrayList<DealEntity> deals = history.get();
            for (DealEntity item : deals) {

                String numFrom = item.getNum_from();
                String numTo = item.getNum_to();
                String direction = "";

                if (numFrom.equals(num) && numTo.equals("брокер")) direction = "списание";
                else if (numFrom.equals(num) && numTo.equals("карта")) direction = "вывод средств";
                else if (numFrom.equals("брокер") && numTo.equals(num)) direction = "поступление";
                else if (numFrom.equals("карта") && numTo.equals(num)) direction = "пополнение c карты";

                DealHistoryResponse dhr = new DealHistoryResponse(num, item.getAmount(), item.getDatetime(), direction);
                result.add(dhr);
            }
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
