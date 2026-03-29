package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.exception.CollectionAccessDeniedException;
import com.erik.pokemonportfolio.collection.domain.exception.CollectionNotFoundException;
import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.util.UUID;

public class RenameCollectionUseCase {

    private final CollectionRepository collectionRepository;

    public RenameCollectionUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Collection execute(UUID collectionId, UUID userId, String newName) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new CollectionNotFoundException("Collection not found"));

        if (!collection.getUserId().equals(userId)) {
            throw new CollectionAccessDeniedException("User is not allowed to modify this collection");
        }

        collection.rename(newName);

        return collectionRepository.save(collection);
    }
}
