package com.erik.pokemonportfolio.portfolio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Portfolio {

    private final UUID collectionId;
    private final UUID userId;
    private final List<ValuatedItem> items;
    private final LocalDateTime calculatedAt;

    public Portfolio(UUID collectionId, UUID userId, List<ValuatedItem> items) {
        if (collectionId == null) {
            throw new IllegalArgumentException("Portfolio collectionId cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("Portfolio userId cannot be null");
        }
        if (items == null) {
            throw new IllegalArgumentException("Portfolio items cannot be null");
        }

        this.collectionId = collectionId;
        this.userId = userId;
        this.items = new ArrayList<>(items);
        this.calculatedAt = LocalDateTime.now();
    }

    public BigDecimal getTotalValue() {
        return items.stream()
                .map(ValuatedItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getCollectionId() {
        return collectionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<ValuatedItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }
}
