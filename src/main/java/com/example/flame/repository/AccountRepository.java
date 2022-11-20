package com.example.flame.repository;

import com.example.flame.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<ArrayList<AccountEntity>> getAllByUsername(String username);

    @Query("update AccountEntity ae set ae.amount = ae.amount + :amount " +
            "where ae.username = :username and ae.currencyId = :currencyId")
    void makeDeal(String username, long currencyId, double amount);

    Optional<AccountEntity> getAllByUsernameAndCurrencyId(String username, long currencyId);
}
