package com.erik.pokemonportfolio.pricing.application.usecase;

import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;
import com.erik.pokemonportfolio.pricing.domain.repository.PriceSnapshotRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RegisterPriceSnapshotUseCase {

    private final PriceSnapshotRepository priceSnapshotRepository;

    public RegisterPriceSnapshotUseCase(PriceSnapshotRepository priceSnapshotRepository) {
        this.priceSnapshotRepository = priceSnapshotRepository;
    }

    public PriceSnapshot execute(UUID cardId, BigDecimal price, String source) {
        PriceSnapshot snapshot = new PriceSnapshot(
                UUID.randomUUID(),
                cardId,
                price,
                source,
                LocalDateTime.now()
        );

        return priceSnapshotRepository.save(snapshot);
    }
}
