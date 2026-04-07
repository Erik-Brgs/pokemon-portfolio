package com.erik.pokemonportfolio.pricing.infrastructure.persistente.repository;

import com.erik.pokemonportfolio.pricing.infrastructure.persistente.entity.PriceSnapshotJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPriceSnapshotRepository extends JpaRepository<PriceSnapshotJpaEntity, UUID> {

    List<PriceSnapshotJpaEntity> findAllByCardId(UUID cardId);

    @Query("SELECT p FROM PriceSnapshotJpaEntity p WHERE p.cardId = :cardId ORDER BY p.capturedAt DESC LIMIT 1")
    Optional<PriceSnapshotJpaEntity> findLatestByCardId(@Param("cardId") UUID cardId);
}
