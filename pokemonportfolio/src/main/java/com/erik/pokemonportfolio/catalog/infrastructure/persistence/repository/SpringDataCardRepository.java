package com.erik.pokemonportfolio.catalog.infrastructure.persistence.repository;

import com.erik.pokemonportfolio.catalog.infrastructure.persistence.entity.CardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCardRepository extends JpaRepository<CardJpaEntity, UUID> {

    Optional<CardJpaEntity> findByExternalId(String externalId);
}
