package com.example.flame.repository;

import com.example.flame.entity.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface DealRepository extends JpaRepository<DealEntity, Long> {

    @Query("select td from DealEntity td where td.datetime >= :from and td.datetime <= :to " +
            "and (td.user_to_username = :username or td.user_from_username = :username) " +
            "and (td.num_from = :num or td.num_to = :num)")
    Optional<ArrayList<DealEntity>> getHistory(LocalDateTime from, LocalDateTime to, String username, String num);
}
