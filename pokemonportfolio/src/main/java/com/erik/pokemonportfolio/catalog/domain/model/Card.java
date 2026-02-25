package com.erik.pokemonportfolio.catalog.domain.model;

import com.erik.pokemonportfolio.catalog.domain.exception.InvalidCardException;

import java.util.UUID;

public class Card {

    private final UUID id;
    private final String externalId;
    private final String name;
    private final String setName;
    private final String language;
    private final String rarity;

    public Card(UUID id, String externalId, String name, String setName, String language, String rarity) {

        if (id == null) {
            throw new InvalidCardException("id cannot be null");
        }

        validateNotBlank(externalId, "externalId");
        validateNotBlank(name, "name");
        validateNotBlank(setName, "setName");
        validateNotBlank(language, "language");
        validateNotBlank(rarity, "rarity");

        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.setName = setName;
        this.language = language;
        this.rarity = rarity;
    }

    private static void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new InvalidCardException(fieldName + " cannot be null or blank");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return id.equals(card.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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
