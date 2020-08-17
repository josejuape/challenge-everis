package com.everis.challenge;

import com.everis.challenge.model.thridparty.User;
import com.everis.challenge.model.api.CurrencyCode;
import com.everis.challenge.model.thridparty.ExchangeRate;
import com.everis.challenge.service.ExchangeRateService;
import com.everis.challenge.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class ChallengeEverisApplication {

	private ExchangeRateService exchangeRateService;

	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeEverisApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
       return args -> {
		   List<ExchangeRate> list = new ArrayList<>();
		   list.add(new ExchangeRate(CurrencyCode.USD.toString(),CurrencyCode.PEN.toString(), BigDecimal.valueOf(3.35)));
		   list.add(new ExchangeRate(CurrencyCode.PEN.toString(),CurrencyCode.USD.toString(), BigDecimal.valueOf(0.28)));
		   list.add(new ExchangeRate(CurrencyCode.EUR.toString(),CurrencyCode.PEN.toString(), BigDecimal.valueOf(4.23)));
		   list.add(new ExchangeRate(CurrencyCode.PEN.toString(),CurrencyCode.EUR.toString(), BigDecimal.valueOf(0.24)));
		   exchangeRateService.save(list);

		   User user = new User("josejuape", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("juape123"));
		   userService.save(user);
       };
	}

}
