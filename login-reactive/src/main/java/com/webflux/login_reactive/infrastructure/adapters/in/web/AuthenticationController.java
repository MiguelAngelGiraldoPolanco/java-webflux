package com.webflux.loginreactive.infrastructure.adapters.in.web;

import com.webflux.loginreactive.application.LoginUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final LoginUserUseCase loginUserUseCase;

    @Autowired
    public AuthenticationController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Authentication>> login(@RequestBody User user) {
        return loginUserUseCase.loginUser(user.getEmail(), user.getPassword())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}