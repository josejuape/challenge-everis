package com.everis.challenge.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Slf4j
@Component
@AllArgsConstructor
public class SecurityContextRepositoryImpl implements ServerSecurityContextRepository {

    private AuthenticationManagerImpl authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        String bearer = "Bearer ";

        log.info("API: SecurityContextRepositoryImpl - {}",serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        return Mono.justOrEmpty(serverWebExchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(b -> b.startsWith(bearer))
                .map(subs -> subs.substring(bearer.length()))
                .flatMap(token ->
                        Mono.just(
                                new UsernamePasswordAuthenticationToken(
                                        token,
                                        token,
                                        Collections.singletonList(
                                                new SimpleGrantedAuthority("ROLE_USER")
                                        )
                                )
                        )
                )
                .flatMap(auth -> authenticationManager.authenticate(auth).map(SecurityContextImpl::new));
    }
}