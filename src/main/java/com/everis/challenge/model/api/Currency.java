package com.everis.challenge.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class Currency implements Serializable {
    private CurrencyCode currencyCode;

    private BigDecimal amount;

}
