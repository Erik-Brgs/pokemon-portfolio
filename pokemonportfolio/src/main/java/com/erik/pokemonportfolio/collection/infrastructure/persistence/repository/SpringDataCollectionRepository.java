package com.erik.pokemonportfolio.collection.infrastructure.persistence.repository;

import com.erik.pokemonportfolio.collection.infrastructure.persistence.entity.CollectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataCollectionRepository extends JpaRepository<CollectionJpaEntity, UUID> {

    List<CollectionJpaEntity> findAllByUserId(UUID userId);
}
