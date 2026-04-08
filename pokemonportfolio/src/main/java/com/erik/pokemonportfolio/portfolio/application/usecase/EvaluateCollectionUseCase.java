package com.erik.pokemonportfolio.portfolio.application.usecase;

import com.erik.pokemonportfolio.collection.application.usecase.GetCollectionByIdUseCase;
import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.model.CollectionItem;
import com.erik.pokemonportfolio.portfolio.domain.model.Portfolio;
import com.erik.pokemonportfolio.portfolio.domain.model.PortfolioSnapshot;
import com.erik.pokemonportfolio.portfolio.domain.model.ValuatedItem;
import com.erik.pokemonportfolio.portfolio.domain.repository.PortfolioSnapshotRepository;
import com.erik.pokemonportfolio.pricing.application.usecase.GetLatestPriceByCardIdUseCase;
import com.erik.pokemonportfolio.pricing.domain.exception.PriceSnapshotNotFoundException;
import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EvaluateCollectionUseCase {

    private final GetCollectionByIdUseCase getCollectionByIdUseCase;
    private final GetLatestPriceByCardIdUseCase getLatestPriceByCardIdUseCase;
    private final PortfolioSnapshotRepository portfolioSnapshotRepository;

    public EvaluateCollectionUseCase(
            GetCollectionByIdUseCase getCollectionByIdUseCase,
            GetLatestPriceByCardIdUseCase getLatestPriceByCardIdUseCase,
            PortfolioSnapshotRepository portfolioSnapshotRepository
    ) {
        this.getCollectionByIdUseCase = getCollectionByIdUseCase;
        this.getLatestPriceByCardIdUseCase = getLatestPriceByCardIdUseCase;
        this.portfolioSnapshotRepository = portfolioSnapshotRepository;
    }

    public PortfolioSnapshot execute(UUID collectionId, UUID userId) {
        Collection collection = getCollectionByIdUseCase.execute(collectionId, userId);

        List<ValuatedItem> valuatedItems = new ArrayList<>();

        for (CollectionItem item : collection.getItems()) {
            BigDecimal unitPrice = BigDecimal.ZERO;

            try {
                PriceSnapshot priceSnapshot = getLatestPriceByCardIdUseCase.execute(item.getCardId());
                unitPrice = priceSnapshot.getPrice();
            } catch (PriceSnapshotNotFoundException e) {
                // Se a carta não tem preço histórico, o valor assumido é 0.
                // A coleção ainda pode ser avaliada com base nas outras cartas.
            }

            valuatedItems.add(new ValuatedItem(item.getCardId(), item.getQuantity(), unitPrice));
        }

        Portfolio portfolio = new Portfolio(collection.getUserId(), collection.getId(), valuatedItems);

        PortfolioSnapshot snapshot = new PortfolioSnapshot(
                UUID.randomUUID(),
                collectionId,
                userId,
                portfolio.getTotalValue(),
                portfolio.getCalculatedAt()
        );

        return portfolioSnapshotRepository.save(snapshot);
    }
}
