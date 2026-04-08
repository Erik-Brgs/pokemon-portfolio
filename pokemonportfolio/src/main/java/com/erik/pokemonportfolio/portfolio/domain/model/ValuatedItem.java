package com.erik.pokemonportfolio.portfolio.domain.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ValuatedItem {

    private final UUID cardId;
    private final int quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal totalPrice;

    public ValuatedItem(UUID cardId, int quantity, BigDecimal unitPrice) {
        if (cardId == null) {
            throw new IllegalArgumentException("Valuated item cardId cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Valuated item quantity must be greater than zero");
        }
        if (unitPrice == null || unitPrice.signum() < 0) {
            throw new IllegalArgumentException("Valuated item unitPrice cannot be null or negative");
        }

        this.cardId = cardId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public UUID getCardId() {
        return cardId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValuatedItem that)) return false;
        return cardId.equals(that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }
}
