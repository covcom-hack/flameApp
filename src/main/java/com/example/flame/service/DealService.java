package com.example.flame.service;

import com.example.flame.entity.DealEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface DealService {
    Optional<ArrayList<DealEntity>> getHistory(LocalDateTime from, LocalDateTime to, String username, String num);
}
