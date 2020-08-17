package com.everis.challenge.repository;

import com.everis.challenge.model.thridparty.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ExchangeRateRepository extends MongoRepository<ExchangeRate, Long> {

    ExchangeRate findByCurrencyOriginAndCurrencyDestiny(String currencyOrigin, String currencyDestiny);

}
