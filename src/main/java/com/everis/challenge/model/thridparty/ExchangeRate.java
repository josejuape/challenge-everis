package com.everis.challenge.model.thridparty;

import com.everis.challenge.model.api.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document(collection = "exchange_rate")
public class ExchangeRate {

    @Id
    private String id;

    private CurrencyCode currencyOrigin;

    private CurrencyCode currencyDestiny;

    private BigDecimal valueCurrency;

    public ExchangeRate(){}

    public ExchangeRate(CurrencyCode currencyOrigin, CurrencyCode currencyDestiny, BigDecimal valueCurrency){
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
