package com.everis.challenge.controller;

import com.everis.challenge.model.api.ExchangeRateListRs;
import com.everis.challenge.model.api.ExchangeRateRq;
import com.everis.challenge.model.api.ExchangeRateRs;
import com.everis.challenge.model.api.UpdateExchangeRateRq;
import com.everis.challenge.service.ExchangeRateService;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("${application.challenge.path}")
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    @PostMapping(path = "/apply",
      consumes = MediaType.APPLICATION_JSON_VALUE)
    public Single<ExchangeRateRs> apply(@RequestBody ExchangeRateRq exchangeRateRq){
      return exchangeRateService.apply(exchangeRateRq);
    }

    @PostMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Single<String> update(@RequestBody UpdateExchangeRateRq updateExchangeRateRq){
        return exchangeRateService.update(updateExchangeRateRq);
    }

    @GetMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Single<ExchangeRateListRs> list(){
        return exchangeRateService.list();
    }
}
