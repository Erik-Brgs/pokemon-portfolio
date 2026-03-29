package com.erik.pokemonportfolio.identity.infrastructure.config;

import com.erik.pokemonportfolio.identity.application.service.TokenService;
import com.erik.pokemonportfolio.identity.application.usecase.LoginUseCase;
import com.erik.pokemonportfolio.identity.application.usecase.RegisterUserUseCase;
import com.erik.pokemonportfolio.identity.domain.repository.UserRepository;
import com.erik.pokemonportfolio.identity.infrastructure.persistence.repository.JpaUserRepository;
import com.erik.pokemonportfolio.identity.infrastructure.persistence.repository.SpringDataUserRepository;
import com.erik.pokemonportfolio.identity.infrastructure.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class IdentityConfiguration {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Bean
    public JpaUserRepository jpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        return new JpaUserRepository(springDataUserRepository);
    }

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return jpaUserRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public TokenService tokenService() {
        return new JwtTokenService(jwtSecret, jwtExpiration);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return new RegisterUserUseCase(userRepository, passwordEncoder);
    }

    @Bean
    public LoginUseCase loginUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        return new LoginUseCase(userRepository, passwordEncoder, tokenService);
    }
}
