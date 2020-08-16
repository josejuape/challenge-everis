package com.everis.challenge.model.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateExchangeRateRq implements Serializable {

    private static final long serialVersionUID = 1;

    @Digits(integer = 6, fraction = 2)
    private BigDecimal valueExchangeRate;

    private CurrencyCode currencyOrigin;

    private CurrencyCode currencyDestiny;
}
