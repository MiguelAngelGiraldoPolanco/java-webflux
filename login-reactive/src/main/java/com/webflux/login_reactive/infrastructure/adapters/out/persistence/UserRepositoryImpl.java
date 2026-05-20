package com.webflux.login_reactive.infrastructure.adapters.out.persistence;

import com.webflux.login_reactive.domain.User;
import com.webflux.login_reactive.domain.UserRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public UserRepositoryImpl(R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return r2dbcEntityTemplate.select(User.class)
                .matching(query(where("email").is(email)))
                .first();
    }

    @Override
    public Mono<Void> save(User user) {
        if (user.getId() == null) {
            return r2dbcEntityTemplate.insert(user);
        } else {
            return r2dbcEntityTemplate.update(user);
        }
    }
}