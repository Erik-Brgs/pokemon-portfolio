package com.erik.pokemonportfolio.identity.application.usecase;

import com.erik.pokemonportfolio.identity.domain.exception.EmailAlreadyExistsException;
import com.erik.pokemonportfolio.identity.domain.model.User;
import com.erik.pokemonportfolio.identity.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public  RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(String email, String displayName, String rawPassword) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new EmailAlreadyExistsException("Email already in use: " + email);
        });

        String passwordHash = passwordEncoder.encode(rawPassword);

        User user = new User(
                UUID.randomUUID(),
                email,
                displayName,
                passwordHash,
                LocalDateTime.now()
        );

        return userRepository.save(user);
    }
}
