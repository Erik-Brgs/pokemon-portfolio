package com.erik.pokemonportfolio.catalog.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "cards")
public class CardJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String externalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String setName;

    @Column(nullable = false)
    private String language;

    @Column
    private String rarity;

    protected CardJpaEntity() {
        //JPA only
    }

    public CardJpaEntity(UUID id, String externalId, String name, String setName, String language, String rarity) {
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
