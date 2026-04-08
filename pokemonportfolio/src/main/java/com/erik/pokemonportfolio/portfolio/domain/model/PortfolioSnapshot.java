package com.erik.pokemonportfolio.portfolio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class PortfolioSnapshot {

    private final UUID id;
    private final UUID collectionId;
    private final UUID userId;
    private final BigDecimal totalValue;
    private final LocalDateTime capturedAt;

    public PortfolioSnapshot(UUID id, UUID collectionId, UUID userId, BigDecimal totalValue, LocalDateTime capturedAt) {
        if (id == null) {
            throw new IllegalArgumentException("Portfolio snapshot id cannot be null");
        }
        if (collectionId == null) {
            throw new IllegalArgumentException("Portfolio snapshot collectionId cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("Portfolio snapshot userId cannot be null");
        }
        if (totalValue == null || totalValue.signum() < 0) {
            throw new IllegalArgumentException("Portfolio snapshot totalValue cannot be null or negative");
        }
        if (capturedAt == null) {
            throw new IllegalArgumentException("Portfolio snapshot capturedAt cannot be null");
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioSnapshot that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}