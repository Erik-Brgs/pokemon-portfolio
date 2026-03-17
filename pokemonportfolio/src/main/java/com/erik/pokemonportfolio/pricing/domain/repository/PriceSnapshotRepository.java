package com.erik.pokemonportfolio.pricing.domain.repository;

import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceSnapshotRepository {

    PriceSnapshot save(PriceSnapshot priceSnapshot);

    List<PriceSnapshot> findByCardId(UUID cardId);

    Optional<PriceSnapshot> findLatestByCardId(UUID cardId);
}
