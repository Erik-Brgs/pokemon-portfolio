package com.erik.pokemonportfolio.collection.domain.repository;

import com.erik.pokemonportfolio.collection.domain.model.Collection;

import java.util.Optional;
import java.util.UUID;

public interface CollectionRepository {

    Collection save(Collection collection);

    Optional<Collection>  findById(UUID id);

    void delete(Collection collection);
}
