package com.everis.challenge;

import com.everis.challenge.model.api.CurrencyCode;
import com.everis.challenge.model.thridparty.ExchangeRate;
import com.everis.challenge.repository.ExchangeRateRepository;
import com.everis.challenge.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChallengeEverisApplication {

	@Autowired
	ExchangeRateService exchangeRateService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeEverisApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
       return args -> {
		   List<ExchangeRate> list = new ArrayList<>();
		   list.add(new ExchangeRate(CurrencyCode.USD,CurrencyCode.PEN, BigDecimal.valueOf(3.35)));
		   list.add(new ExchangeRate(CurrencyCode.PEN,CurrencyCode.USD, BigDecimal.valueOf(0.28)));
		   list.add(new ExchangeRate(CurrencyCode.EUR,CurrencyCode.PEN, BigDecimal.valueOf(4.23)));
		   list.add(new ExchangeRate(CurrencyCode.PEN,CurrencyCode.EUR, BigDecimal.valueOf(0.24)));
		   exchangeRateService.save(list);
	   };
	}

}
