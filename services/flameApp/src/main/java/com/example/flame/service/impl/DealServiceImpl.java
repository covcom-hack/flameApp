package com.example.flame.service.impl;

import com.example.flame.entity.DealEntity;
import com.example.flame.repository.DealRepository;
import com.example.flame.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    @Override
    public Optional<ArrayList<DealEntity>> getHistory(LocalDateTime from, LocalDateTime to, String username, String num) {
        return dealRepository.getHistory(from, to, username, num);
    }
}
