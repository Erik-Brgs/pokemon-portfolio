package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.util.UUID;

public class RenameCollectionUseCase {

    private final CollectionRepository collectionRepository;

    public RenameCollectionUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    private void execute(UUID userId, UUID collectionId, String newName) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found"));

        if (!collection.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User is not allowed to modify this collection");
        }

        collection.rename(newName);

        collectionRepository.save(collection);
    }
}
