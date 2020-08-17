package com.everis.challenge.repository;

import com.everis.challenge.model.api.CurrencyCode;
import com.everis.challenge.model.thridparty.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.ResultSet;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query(value = "SELECT * FROM ExchangeRate e WHERE e.currencyOrigin = ?1 AND e.currencyDestiny= ?2", nativeQuery = true)
    ExchangeRate findByCurrencyOriginAndCurrencyDestiny(String currencyOrigin, String currencyDestiny);

}
