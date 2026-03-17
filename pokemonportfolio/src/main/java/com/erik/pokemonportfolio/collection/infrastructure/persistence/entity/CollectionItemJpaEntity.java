package com.erik.pokemonportfolio.collection.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "collection_items")
public class CollectionItemJpaEntity {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "collection_id", nullable = false)
    private CollectionJpaEntity collection;

    @Column(name = "card_id", nullable = false)
    private UUID cardId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String condition;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected CollectionItemJpaEntity() {
    }

    public CollectionItemJpaEntity(UUID id, UUID cardId, int quantity, String condition, LocalDateTime createdAt) {
        this.id = id;
        this.cardId = cardId;
        this.quantity = quantity;
        this.condition = condition;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public CollectionJpaEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionJpaEntity collection) {
        this.collection = collection;
    }

    public UUID getCardId() {
        return cardId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCondition() {
        return condition;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
