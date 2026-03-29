package com.erik.pokemonportfolio.collection.domain.model;

import com.erik.pokemonportfolio.collection.domain.exception.CollectionItemNotFoundException;

import java.time.LocalDateTime;
import java.util.*;

public class Collection {

    private final UUID id;
    private final UUID userId;
    private String name;
    private final LocalDateTime createdAt;
    private final List<CollectionItem> items;

    public Collection(UUID id, UUID userId, String name, LocalDateTime createdAt) {
        this(id, userId, name, createdAt, new ArrayList<>());
    }

    public Collection(UUID id, UUID userId, String name, LocalDateTime createdAt, List<CollectionItem> items) {
        if (id == null) {
            throw new IllegalArgumentException("Collection id cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("Collection userId cannot be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be blank");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("Collection createdAt cannot be null");
        }
        if (items == null) {
            throw new IllegalArgumentException("Collection items cannot be null");
        }

        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
        this.items = new ArrayList<>(items);
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be blank");
        }
        this.name = newName;
    }

    public void addItem(CollectionItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Collection item cannot be null");
        }
        this.items.add(item);
    }

    public void removeItem(UUID itemId) {
        if (itemId == null) {
            throw new IllegalArgumentException("Collection item id cannot be null");
        }

        boolean removed = this.items.removeIf(item -> item.getId().equals(itemId));

        if (!removed) {
            throw new CollectionItemNotFoundException("Collection item not found");
        }
    }

    public int getTotalQuantity() {
        return items.stream()
                .mapToInt(CollectionItem::getQuantity)
                .sum();
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

    public List<CollectionItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collection that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
