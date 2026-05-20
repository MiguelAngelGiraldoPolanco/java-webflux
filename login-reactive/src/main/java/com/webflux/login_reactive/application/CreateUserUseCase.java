package com.webflux.loginreactive.application;

import com.webflux.loginreactive.domain.User;
import com.webflux.loginreactive.domain.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createUser(String email, String password, Boolean isAdmin) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(isAdmin);
        return userRepository.save(user);
    }
}