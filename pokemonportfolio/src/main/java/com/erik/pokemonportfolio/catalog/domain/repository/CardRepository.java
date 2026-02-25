package com.erik.pokemonportfolio.catalog.domain.repository;

import com.erik.pokemonportfolio.catalog.domain.model.Card;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository {

    Card save(Card card);

    Optional<Card> findById(UUID id);

    Optional<Card> findByExternalId(String externalId);
}
