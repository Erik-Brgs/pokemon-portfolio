package com.erik.pokemonportfolio.collection.api.dto;

import java.util.List;
import java.util.UUID;

public class CollectionResponse {

    private final UUID id;
    private final UUID userId;
    private final String name;
    private final List<CollectionItemResponse> items;

    public CollectionResponse(UUID id, UUID userId, String name, List<CollectionItemResponse> items) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<CollectionItemResponse> getItems() {
        return items;
    }
}
