package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.model.CollectionItem;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class AddItemToCollectionUseCase {

    private final CollectionRepository collectionRepository;

    public AddItemToCollectionUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Collection execute(
            UUID collectionId,
            UUID userId,
            UUID cardId,
            int quantity,
            String condition
    ) {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection not found"));

        if (!collection.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User is not allowed to modify this collection");
        }

        CollectionItem item = new CollectionItem(
                UUID.randomUUID(),
                cardId,
                quantity,
                condition,
                LocalDateTime.now()
                );

        collection.addItem(item);

        return collectionRepository.save(collection);
    }
}
