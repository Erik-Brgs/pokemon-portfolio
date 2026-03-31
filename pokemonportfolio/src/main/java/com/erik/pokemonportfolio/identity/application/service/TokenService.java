package com.erik.pokemonportfolio.identity.application.service;

import com.erik.pokemonportfolio.identity.domain.model.User;

public interface TokenService {

    String generateToken(User user);
    boolean isTokenValid(String token);
    String extractUserId(String token);
}
