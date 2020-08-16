package com.everis.challenge.service;

import com.everis.challenge.model.api.ExchangeRateListRs;
import com.everis.challenge.model.api.ExchangeRateRq;
import com.everis.challenge.model.api.ExchangeRateRs;
import com.everis.challenge.model.api.UpdateExchangeRateRq;
import com.everis.challenge.model.thridparty.ExchangeRate;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeRateService {

    Single<ExchangeRateRs> apply(ExchangeRateRq exchangeRateRq);
    void save(List<ExchangeRate> list);
    Single<String> update(UpdateExchangeRateRq updateExchangeRateRq);
    Single<ExchangeRateListRs> list();
}
