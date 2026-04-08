package com.erik.pokemonportfolio.portfolio.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "portfolio_snapshots")
public class PortfolioSnapshotJpaEntity {

    @Id
    private UUID id;

    @Column(name = "collection_id", nullable = false)
    private UUID collectionId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "captured_at", nullable = false)
    private LocalDateTime capturedAt;

    protected PortfolioSnapshotJpaEntity() {}

    public PortfolioSnapshotJpaEntity(UUID id, UUID collectionId, UUID userId, BigDecimal totalValue, LocalDateTime capturedAt) {
        this.id = id;
        this.collectionId = collectionId;
        this.userId = userId;
        this.totalValue = totalValue;
        this.capturedAt = capturedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCollectionId() {
        return collectionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public LocalDateTime getCapturedAt() {
        return capturedAt;
    }
}