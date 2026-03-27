package com.erik.pokemonportfolio.collection.api.dto;

import java.util.UUID;

public class CreateCollectionRequest {

    private UUID userId;
    private String name;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
