package com.erik.pokemonportfolio.collection.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "collections")
public class CollectionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollectionItemJpaEntity> items = new ArrayList<>();

    protected CollectionJpaEntity() {
    }

    public CollectionJpaEntity(UUID id, UUID userId, String name, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<CollectionItemJpaEntity> getItems() {
        return items;
    }

    public void setItems(List<CollectionItemJpaEntity> items) {
        this.items.clear();

        if (items != null) {
            for (CollectionItemJpaEntity item : items) {
                addItem(item);
            }
        }
    }

    public void addItem(CollectionItemJpaEntity item) {
        item.setCollection(this);
        this.items.add(item);
    }
}
