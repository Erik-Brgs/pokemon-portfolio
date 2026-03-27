package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.util.UUID;

public class GetCollectionByIdUseCase {

    private final CollectionRepository  collectionRepository;

    public GetCollectionByIdUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Collection execute(UUID collectionId, UUID userId) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found"));

        if (!collection.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Collection does not belong to the informed user");
        }

        return collection;
    }
}
