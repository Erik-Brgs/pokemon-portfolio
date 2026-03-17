package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.util.UUID;

public class RemoveItemFromCollectionUseCase {

    private final CollectionRepository  collectionRepository;

    public RemoveItemFromCollectionUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public void execute(UUID userId, UUID collectionId, UUID itemId) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found"));

        if (!collection.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User is not allowed to modify this collection");
        }

        collection.removeItem(itemId);

        collectionRepository.save(collection);
    }
}
