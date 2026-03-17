package com.erik.pokemonportfolio.pricing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class PriceSnapshot {

    private final UUID id;
    private final UUID cardId;
    private final BigDecimal price;
    private final String source;
    private final LocalDateTime capturedAt;

    public PriceSnapshot(UUID id, UUID cardId, BigDecimal price, String source, LocalDateTime capturedAt) {
        if (id == null) {
            throw new IllegalArgumentException("Price snapshot id cannot be null");
        }
        if (cardId == null) {
            throw new IllegalArgumentException("Price snapshot cardId cannot be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price snapshot price cannot be null");
        }
        if (price.signum() < 0) {
            throw new IllegalArgumentException("Price snapshot price cannot be negative");
        }
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("Price snapshot source cannot be blank");
        }
        if (capturedAt == null) {
            throw new IllegalArgumentException("Price snapshot capturedAt cannot be null");
        }

        this.id = id;
        this.cardId = cardId;
        this.price = price;
        this.source = source;
        this.capturedAt = capturedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCardId() {
        return cardId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getCapturedAt() {
        return capturedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceSnapshot that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
