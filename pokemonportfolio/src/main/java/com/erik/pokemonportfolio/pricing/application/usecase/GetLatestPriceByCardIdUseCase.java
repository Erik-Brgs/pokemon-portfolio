package com.erik.pokemonportfolio.pricing.application.usecase;

import com.erik.pokemonportfolio.pricing.domain.exception.PriceSnapshotNotFoundException;
import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;
import com.erik.pokemonportfolio.pricing.domain.repository.PriceSnapshotRepository;

import java.util.UUID;

public class GetLatestPriceByCardIdUseCase {

    private final PriceSnapshotRepository priceSnapshotRepository;

    public GetLatestPriceByCardIdUseCase(PriceSnapshotRepository priceSnapshotRepository) {
        this.priceSnapshotRepository = priceSnapshotRepository;
    }

    public PriceSnapshot execute(UUID cardId) {
        return priceSnapshotRepository.findLatestByCardId(cardId)
                .orElseThrow(() -> new PriceSnapshotNotFoundException(
                        "No price snapshot found for card: " + cardId
                ));
    }
}
