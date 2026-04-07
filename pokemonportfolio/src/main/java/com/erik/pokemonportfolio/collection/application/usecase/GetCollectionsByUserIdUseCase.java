package com.erik.pokemonportfolio.collection.application.usecase;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;

import java.util.List;
import java.util.UUID;

public class GetCollectionsByUserIdUseCase {

    private final CollectionRepository collectionRepository;

    public GetCollectionsByUserIdUseCase(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> execute(UUID userId) {
        return collectionRepository.findAllByUserId(userId);
    }
}
