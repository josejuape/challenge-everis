package com.everis.challenge.model.thridparty;

import com.everis.challenge.model.api.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;

    private String currencyOrigin;

    private String currencyDestiny;

    private BigDecimal valueCurrency;

    public ExchangeRate(){}

    public ExchangeRate(String currencyOrigin, String currencyDestiny, BigDecimal valueCurrency){
        this.currencyOrigin = currencyOrigin;
        this.currencyDestiny = currencyDestiny;
        this.valueCurrency = valueCurrency;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id='" + id + '\'' +
                ", currencyOrigin=" + currencyOrigin +
                ", currencyDestiny=" + currencyDestiny +
                ", valueCurrency=" + valueCurrency +
                '}';
    }
}
