package com.erik.pokemonportfolio.catalog.application.usecase;

import com.erik.pokemonportfolio.catalog.domain.exception.CardAlreadyExistsException;
import com.erik.pokemonportfolio.catalog.domain.model.Card;
import com.erik.pokemonportfolio.catalog.domain.repository.CardRepository;

public class RegisterCardUseCase {

    private final CardRepository cardRepository;

    public RegisterCardUseCase(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card execute(String externalId, String name, String setName, String language, String rarity) {
        if (cardRepository.findByExternalId(externalId).isPresent()) {
            throw new CardAlreadyExistsException(externalId);
        }

        Card card = new Card(
                java.util.UUID.randomUUID(),
                externalId,
                name,
                setName,
                language,
                rarity
        );

        return cardRepository.save(card);
    }
}
