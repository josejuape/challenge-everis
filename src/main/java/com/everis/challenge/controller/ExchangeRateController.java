package com.everis.challenge.controller;

import com.everis.challenge.config.AuthResponse;
import com.everis.challenge.config.JWTUtil;
import com.everis.challenge.model.thridparty.User;
import com.everis.challenge.model.api.ExchangeRateRq;
import com.everis.challenge.model.api.ExchangeRateRs;
import com.everis.challenge.model.api.UpdateExchangeRateRq;
import com.everis.challenge.model.api.UserApi;
import com.everis.challenge.model.thridparty.ExchangeRate;
import com.everis.challenge.repository.UserRepository;
import com.everis.challenge.service.ExchangeRateService;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("${application.challenge.path}")
public class ExchangeRateController {

    private ExchangeRateService exchangeRateService;

    private UserRepository userRepository;

    private JWTUtil jwtUtil;

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
    public Flowable<ExchangeRate> list(){
        return exchangeRateService.list();
    }

    @PostMapping(path = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Single<AuthResponse> getToken(@RequestBody UserApi userApi) {
        User user = new User();
        user.setUsername(userApi.getUsername());
        user.setPassword(userApi.getPassword());

        UserDetails userDetails = userRepository.findByUsername(user.getUsername());
        log.info("UserDetails: {}",userDetails);

        if(user.getUsername().equals(userDetails.getUsername())) {
            return Single.just(new AuthResponse(jwtUtil.generateToken(user)));
        }
        return Single.just(new AuthResponse("Error: Usuario no existe!"));
    }


}
