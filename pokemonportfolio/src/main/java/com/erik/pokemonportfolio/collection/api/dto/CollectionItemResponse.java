package com.erik.pokemonportfolio.collection.api.dto;

import java.util.UUID;

public class CollectionItemResponse {

    private final UUID itemId;
    private final UUID cardId;
    private final Integer quantity;
    private final String condition;

    public CollectionItemResponse(UUID itemId, UUID cardId, Integer quantity, String condition) {
        this.itemId = itemId;
        this.cardId = cardId;
        this.quantity = quantity;
        this.condition = condition;
    }

    public UUID getItemId() {
        return itemId;
    }

    public UUID getCardId() {
        return cardId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCondition() {
        return condition;
    }
}
