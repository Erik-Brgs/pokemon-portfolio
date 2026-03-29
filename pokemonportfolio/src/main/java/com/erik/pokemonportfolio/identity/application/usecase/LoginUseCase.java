package com.erik.pokemonportfolio.identity.application.usecase;

import com.erik.pokemonportfolio.identity.application.service.TokenService;
import com.erik.pokemonportfolio.identity.domain.exception.InvalidCredentialsException;
import com.erik.pokemonportfolio.identity.domain.model.User;
import com.erik.pokemonportfolio.identity.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginUseCase(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        TokenService tokenService)
    {
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String execute(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return tokenService.generateToken(user);
    }
}
