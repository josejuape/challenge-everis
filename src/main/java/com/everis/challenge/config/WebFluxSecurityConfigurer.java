package com.everis.challenge.config;

import com.everis.challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
public class WebFluxSecurityConfigurer {

    private UserRepository userRepository;
    private AuthenticationManagerImpl authenticationManager;
    private SecurityContextRepositoryImpl securityContextRepository;

    @Bean
    ReactiveUserDetailsService userDetailsService() {
        return (name) -> Mono.justOrEmpty(userRepository.findByUsername(name));
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

        log.info("filterChain: {}",http.toString());

        return http.authorizeExchange(
                authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/exchange-rate/v1/token").permitAll()
                        .anyExchange().authenticated()
        )
                .exceptionHandling()
                .authenticationEntryPoint((response, error) -> Mono.fromRunnable(() -> {
                    response.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((response, error) -> Mono.fromRunnable(() -> {
                    response.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                })).and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .requestCache().requestCache(NoOpServerRequestCache.getInstance())
                .and()
                .build();
    }

}
