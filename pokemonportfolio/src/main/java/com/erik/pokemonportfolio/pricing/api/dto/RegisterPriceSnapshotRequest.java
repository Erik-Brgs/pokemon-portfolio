package com.erik.pokemonportfolio.pricing.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class RegisterPriceSnapshotRequest {

    private UUID cardId;
    private BigDecimal price;
    private String source;

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
