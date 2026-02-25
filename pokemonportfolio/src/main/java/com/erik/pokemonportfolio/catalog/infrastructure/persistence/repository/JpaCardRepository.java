package com.erik.pokemonportfolio.catalog.infrastructure.persistence.repository;

import com.erik.pokemonportfolio.catalog.domain.model.Card;
import com.erik.pokemonportfolio.catalog.domain.repository.CardRepository;
import com.erik.pokemonportfolio.catalog.infrastructure.persistence.entity.CardJpaEntity;

import java.util.Optional;
import java.util.UUID;

public class JpaCardRepository implements CardRepository {

    private final SpringDataCardRepository springRepository;

    public JpaCardRepository(SpringDataCardRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Card save(Card card) {
        CardJpaEntity entity = toEntity(card);

        CardJpaEntity saved = springRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<Card> findById(UUID id) {
        return springRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Card> findByExternalId(String externalId) {
        return springRepository.findByExternalId(externalId)
                .map(this::toDomain);
    }

    private CardJpaEntity toEntity(Card card) {
        return new CardJpaEntity(
                card.getId(),
                card.getExternalId(),
                card.getName(),
                card.getSetName(),
                card.getLanguage(),
                card.getRarity()
        );
    }

    private Card toDomain(CardJpaEntity entity) {
        return new Card(
                entity.getId(),
                entity.getExternalId(),
                entity.getName(),
                entity.getSetName(),
                entity.getLanguage(),
                entity.getRarity()
        );
    }
}
