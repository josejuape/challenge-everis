package com.everis.challenge.service.impl;

import com.everis.challenge.model.api.*;
import com.everis.challenge.model.thridparty.ExchangeRate;
import com.everis.challenge.repository.ExchangeRateRepository;
import com.everis.challenge.service.ExchangeRateService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private ExchangeRateRepository exchangeRateRepository;

  @Override
  public Single<ExchangeRateRs> apply(ExchangeRateRq exchangeRateRq) {


    return Single.just(mapperToExchangeRate(exchangeRateRq))
            .map(ex -> exchangeRateRepository
                    .findByCurrencyOriginAndCurrencyDestiny(ex.getCurrencyOrigin().name(),ex.getCurrencyDestiny().name()))
            .map(result -> this.calculateExchangeRate(exchangeRateRq, result))
            .subscribeOn(Schedulers.io());
  }

    public void save(List<ExchangeRate> list) {
      list.stream().forEach(exchangeRateRepository::save);
    }

    public Single<String> update(UpdateExchangeRateRq updateExchangeRateRq) {
      return Single.just(Optional.ofNullable(exchangeRateRepository
                .findByCurrencyOriginAndCurrencyDestiny(updateExchangeRateRq.getCurrencyOrigin().name(),
                        updateExchangeRateRq.getCurrencyDestiny().name()))
                .map(ex -> {
                    log.info("ExchangeRate find: {}",ex);
                    if (Objects.nonNull(ex)){
                        ex.setValueCurrency(updateExchangeRateRq.getValueExchangeRate());
                        ExchangeRate res = exchangeRateRepository.save(ex);
                        log.info("ExchangeRate changed: {}",res);
                        return "successful operation!";
                    }else{
                        return "Currency code not found!";
                    }
                }).get());
    }

    public Single<ExchangeRateListRs> list(){
      ExchangeRateListRs exchangeRateListRs = new ExchangeRateListRs();
      exchangeRateListRs.setLstExchangeRate(exchangeRateRepository.findAll());
      return Single.just(exchangeRateListRs);
    }

    private ExchangeRate  mapperToExchangeRate(ExchangeRateRq exchangeRateRq){
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrencyOrigin(exchangeRateRq.getCurrencyOrigin());
        exchangeRate.setCurrencyDestiny(exchangeRateRq.getCurrencyDestiny());
        return exchangeRate;
    }

    private ExchangeRateRs calculateExchangeRate( ExchangeRateRq exchangeRateRq, ExchangeRate result){
        ExchangeRateRs exchangeRateRs = new ExchangeRateRs();
        Currency currencyOrigin = new Currency();
        Currency currencyDestiny = new Currency();

        log.info("ExchangeRate: {}",result);

        currencyOrigin.setCurrencyCode(result.getCurrencyOrigin());
        currencyOrigin.setAmount(exchangeRateRq.getAmount());
        currencyDestiny.setAmount(exchangeRateRq.getAmount().multiply(result.getValueCurrency()));
        currencyDestiny.setCurrencyCode(result.getCurrencyDestiny());

        exchangeRateRs.setMoneyOrigin(currencyOrigin);
        exchangeRateRs.setMoneyDestiny(currencyDestiny);
        exchangeRateRs.setExchangeRate(result.getValueCurrency());
        return exchangeRateRs;
    }
}
