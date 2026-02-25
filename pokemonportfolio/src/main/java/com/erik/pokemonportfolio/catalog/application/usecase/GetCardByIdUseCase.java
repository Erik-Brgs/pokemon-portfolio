package com.erik.pokemonportfolio.catalog.application.usecase;

import com.erik.pokemonportfolio.catalog.domain.exception.CardNotFoundException;
import com.erik.pokemonportfolio.catalog.domain.model.Card;
import com.erik.pokemonportfolio.catalog.domain.repository.CardRepository;

import java.util.UUID;

public class GetCardByIdUseCase {

    private final CardRepository cardRepository;

    public GetCardByIdUseCase(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card execute(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
    }
}
