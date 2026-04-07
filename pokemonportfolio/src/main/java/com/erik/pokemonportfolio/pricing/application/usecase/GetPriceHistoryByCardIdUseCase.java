package com.erik.pokemonportfolio.pricing.application.usecase;

import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;
import com.erik.pokemonportfolio.pricing.domain.repository.PriceSnapshotRepository;

import java.util.List;
import java.util.UUID;

public class GetPriceHistoryByCardIdUseCase {

    private final PriceSnapshotRepository priceSnapshotRepository;

    public GetPriceHistoryByCardIdUseCase(PriceSnapshotRepository priceSnapshotRepository) {
        this.priceSnapshotRepository = priceSnapshotRepository;
    }

    public List<PriceSnapshot> execute(UUID cardId) {
        return priceSnapshotRepository.findByCardId(cardId);
    }
}
