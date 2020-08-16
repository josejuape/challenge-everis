package com.everis.challenge.model.api;

import com.everis.challenge.model.thridparty.ExchangeRate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ExchangeRateListRs implements Serializable {

    private List<ExchangeRate> lstExchangeRate;

}
