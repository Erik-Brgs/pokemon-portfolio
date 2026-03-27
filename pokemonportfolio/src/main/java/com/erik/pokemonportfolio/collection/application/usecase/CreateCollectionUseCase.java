package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateCollectionUseCase {

    private final CollectionRepository collectionRepository;

    public CreateCollectionUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Collection execute(UUID userId, String name) {
        Collection collection = new Collection(
                UUID.randomUUID(),
                userId,
                name,
                LocalDateTime.now()
        );

        collectionRepository.save(collection);

        return collection;
    }
}
