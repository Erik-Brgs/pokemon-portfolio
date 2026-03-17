package com.erik.pokemonportfolio.collection.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class CollectionItem {

    private final UUID id;
    private final UUID cardId;
    private final int quantity;
    private final String condition;
    private final LocalDateTime createdAt;

    public CollectionItem(UUID id, UUID cardId, int quantity, String condition, LocalDateTime createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("Collection item id cannot be null");
        }

        if (cardId == null) {
            throw new IllegalArgumentException("Collection item cardId cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Collection item quantity must be greater than zero");
        }

        if (condition == null || condition.isBlank()) {
            throw new IllegalArgumentException("Collection item condition cannot be blank");
        }

        if (createdAt == null) {
            throw new IllegalArgumentException("Collection item createdAt cannot be null");
        }

        this.id = id;
        this.cardId = cardId;
        this.quantity = quantity;
        this.condition = condition;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCardId() {
        return cardId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCondition() {
        return condition;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionItem that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
