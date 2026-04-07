package com.erik.pokemonportfolio.pricing.infrastructure.persistente.repository;

import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;
import com.erik.pokemonportfolio.pricing.domain.repository.PriceSnapshotRepository;
import com.erik.pokemonportfolio.pricing.infrastructure.persistente.entity.PriceSnapshotJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JpaPriceSnapshotRepository implements PriceSnapshotRepository {

    private final SpringDataPriceSnapshotRepository springDataPriceSnapshotRepository;

    public JpaPriceSnapshotRepository(SpringDataPriceSnapshotRepository springDataPriceSnapshotRepository) {
        this.springDataPriceSnapshotRepository = springDataPriceSnapshotRepository;
    }

    @Override
    public PriceSnapshot save(PriceSnapshot snapshot) {
        PriceSnapshotJpaEntity entity = toEntity(snapshot);
        PriceSnapshotJpaEntity saved = springDataPriceSnapshotRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<PriceSnapshot> findByCardId(java.util.UUID cardId) {
        return springDataPriceSnapshotRepository.findAllByCardId(cardId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<PriceSnapshot> findLatestByCardId(UUID cardId) {
        return springDataPriceSnapshotRepository.findLatestByCardId(cardId)
                .map(this::toDomain);
    }

    private PriceSnapshotJpaEntity toEntity(PriceSnapshot snapshot) {
        return new PriceSnapshotJpaEntity(
                snapshot.getId(),
                snapshot.getCardId(),
                snapshot.getPrice(),
                snapshot.getSource(),
                snapshot.getCapturedAt()
        );
    }

    private PriceSnapshot toDomain(PriceSnapshotJpaEntity entity) {
        return new PriceSnapshot(
                entity.getId(),
                entity.getCardId(),
                entity.getPrice(),
                entity.getSource(),
                entity.getCapturedAt()
        );
    }
}
