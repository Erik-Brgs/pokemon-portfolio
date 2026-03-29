package com.erik.pokemonportfolio.identity.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String email;
    private String displayName;
    private final String passwordHash;
    private final LocalDateTime createdAt;

    public User(UUID id, String email, String displayName,String passwordHash, LocalDateTime createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        if (email == null) {
            throw new IllegalArgumentException("User email cannot be blank");
        }

        if (displayName == null) {
            throw new IllegalArgumentException("User display name cannot be blank");
        }

        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("User passwordHash cannot be blank");
        }

        if (createdAt == null) {
            throw new IllegalArgumentException("User createdAt cannot be null");
        }

        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    public void changeDisplayName(String newDisplayName) {
        if (newDisplayName == null ||  newDisplayName.isBlank()) {
            throw new IllegalArgumentException("New display name cannot be blank");
        }
        this.displayName = newDisplayName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
