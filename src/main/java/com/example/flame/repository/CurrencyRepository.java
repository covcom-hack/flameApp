package com.example.flame.repository;

import com.example.flame.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

}
