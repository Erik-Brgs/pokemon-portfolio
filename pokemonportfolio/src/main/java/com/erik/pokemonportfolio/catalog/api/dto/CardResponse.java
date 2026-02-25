package com.erik.pokemonportfolio.catalog.api.dto;

import java.util.UUID;

public class CardResponse {

    private UUID id;
    private String externalId;
    private String name;
    private String setName;
    private String language;
    private String rarity;

    public CardResponse(UUID id, String externalId, String name, String setName, String language, String rarity) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.setName = setName;
        this.language = language;
        this.rarity = rarity;
    }

    public UUID getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public String getSetName() {
        return setName;
    }

    public String getLanguage() {
        return language;
    }

    public String getRarity() {
        return rarity;
    }
}