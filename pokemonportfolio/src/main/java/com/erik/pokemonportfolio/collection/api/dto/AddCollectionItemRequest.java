package com.erik.pokemonportfolio.collection.api.dto;

import java.util.UUID;

public class AddCollectionItemRequest {

    private UUID cardId;
    private Integer quantity;
    private String condition;

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
