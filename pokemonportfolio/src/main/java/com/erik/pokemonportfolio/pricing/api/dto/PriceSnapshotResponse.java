package com.erik.pokemonportfolio.pricing.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PriceSnapshotResponse {

    private final UUID id;
    private final UUID cardId;
    private final BigDecimal price;
    private final String source;
    private final LocalDateTime capturedAt;

    public PriceSnapshotResponse(UUID id, UUID cardId, BigDecimal price, String source, LocalDateTime capturedAt) {
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
}
