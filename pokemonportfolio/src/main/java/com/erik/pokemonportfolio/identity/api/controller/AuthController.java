package com.erik.pokemonportfolio.identity.api.controller;

import com.erik.pokemonportfolio.identity.api.dto.LoginRequest;
import com.erik.pokemonportfolio.identity.api.dto.LoginResponse;
import com.erik.pokemonportfolio.identity.api.dto.RegisterUserRequest;
import com.erik.pokemonportfolio.identity.api.dto.UserResponse;
import com.erik.pokemonportfolio.identity.application.usecase.LoginUseCase;
import com.erik.pokemonportfolio.identity.application.usecase.RegisterUserUseCase;
import com.erik.pokemonportfolio.identity.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUseCase loginUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register (@RequestBody RegisterUserRequest request) {
        User user = registerUserUseCase.execute(
                request.getEmail(),
                request.getDisplayName(),
                request.getPassword()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = loginUseCase.execute(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(new  LoginResponse(token));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getDisplayName()
        );
    }
}
