package com.example.flame.repository;

import com.example.flame.entity.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface DealRepository extends JpaRepository<DealEntity, Long> {
    @Query(value = "select td from DealEntity td where td.dateTime >= :from and td.dateTime <= :to " +
            "and (td.userTo = :username or td.userFrom = :username)" +
            "and (td.numFrom = :num or td.numTo = :num)")
    Optional<ArrayList<DealEntity>> getHistory(LocalDateTime from, LocalDateTime to, String username, String num);
}
