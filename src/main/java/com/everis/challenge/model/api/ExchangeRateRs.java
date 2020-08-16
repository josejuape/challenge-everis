package com.everis.challenge.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class ExchangeRateRs implements Serializable {
    private static final long serialVersionUID = 1;

    private Currency moneyOrigin;

    private Currency moneyDestiny;

    private BigDecimal exchangeRate;
}
