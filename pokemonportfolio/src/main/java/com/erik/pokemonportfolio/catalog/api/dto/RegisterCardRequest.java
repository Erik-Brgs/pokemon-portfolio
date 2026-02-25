package com.erik.pokemonportfolio.catalog.api.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterCardRequest {

    @NotBlank
    private String externalId;

    @NotBlank
    private String name;

    @NotBlank
    private String setName;

    @NotBlank
    private String language;

    @NotBlank
    private String rarity;

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