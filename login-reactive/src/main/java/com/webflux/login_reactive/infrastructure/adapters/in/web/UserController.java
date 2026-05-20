package com.webflux.loginreactive.infrastructure.adapters.in.web;

import com.webflux.loginreactive.application.CreateUserUseCase;
import com.webflux.loginreactive.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(CreateUserUseCase createUserUseCase, PasswordEncoder passwordEncoder) {
        this.createUserUseCase = createUserUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public Mono<ResponseEntity<User>> createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return createUserUseCase.createUser(user.getEmail(), encodedPassword, user.isAdmin())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}