package com.webflux.loginreactive.domain;

import reactor.core.publisher.Mono;

public interface UserRepositoryPort {
    Mono<User> findByEmail(String email);
    Mono<Void> save(User user);
}