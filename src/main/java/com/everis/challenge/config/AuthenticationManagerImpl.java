package com.everis.challenge.config;

import com.everis.challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationManagerImpl implements ReactiveAuthenticationManager {

    private JWTUtil jwtUtil;

    private UserRepository userRepository;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String userName = jwtUtil.getUsernameFromToken(token);

        log.info("AuthenticationManager - user: {}, {}",userName ,token);

        return Mono.justOrEmpty(userRepository.findByUsername(userName))
                .flatMap(userDetails -> {
                    log.info("AuthenticationManager - user -intro 1: {}",userDetails);
                    if(userName.equals(userDetails.getUsername()) && jwtUtil.isTokenValidated(token)) {
                        log.info("AuthenticationManager - user -intro 2: {}",userName);
                        return Mono.just(authentication);
                    } else {
                        log.info("AuthenticationManager - user - empty");
                        return Mono.empty();
                    }
                });

    }
}
