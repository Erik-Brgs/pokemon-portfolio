package com.erik.pokemonportfolio.catalog.infrastructure.repository;

import com.erik.pokemonportfolio.catalog.domain.model.Card;
import com.erik.pokemonportfolio.catalog.domain.repository.CardRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCardRepository implements CardRepository {

    private final Map<UUID, Card> cardsById = new HashMap<>();
    private final Map<String, Card> cardsByExternalId = new HashMap<>();

    @Override
    public Card save(Card card) {
        cardsById.put(card.getId(), card);
        cardsByExternalId.put(card.getExternalId(), card);
        return card;
    }

    @Override
    public Optional<Card> findById(UUID id) {
        return Optional.ofNullable(cardsById.get(id));
    }

    @Override
    public Optional<Card> findByExternalId(String externalId) {
        return Optional.ofNullable(cardsByExternalId.get(externalId));
    }
}
