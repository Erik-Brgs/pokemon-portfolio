package com.erik.pokemonportfolio.portfolio.domain.repository;

import com.erik.pokemonportfolio.portfolio.domain.model.PortfolioSnapshot;

import java.util.List;
import java.util.UUID;

public interface PortfolioSnapshotRepository {

    PortfolioSnapshot save(PortfolioSnapshot snapshot);

    List<PortfolioSnapshot> findAllByCollectionId(UUID collectionId);

    List<PortfolioSnapshot> findAllByUserId(UUID userId);
}
