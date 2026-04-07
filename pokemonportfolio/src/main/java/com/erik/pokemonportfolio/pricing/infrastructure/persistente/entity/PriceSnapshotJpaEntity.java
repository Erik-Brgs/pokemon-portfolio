package com.erik.pokemonportfolio.pricing.infrastructure.persistente.entity;

import com.erik.pokemonportfolio.pricing.domain.repository.PriceSnapshotRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "price_snapshots")
public class PriceSnapshotJpaEntity {

    @Id
    private UUID id;

    @Column(name = "card_id", nullable = false)
    private UUID cardId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String source;

    @Column(name = "captured_at", nullable = false)
    private LocalDateTime capturedAt;

    public PriceSnapshotJpaEntity() {}

    public PriceSnapshotJpaEntity(UUID id, UUID cardId, BigDecimal price, String source, LocalDateTime capturedAt) {
        this.id = id;
        this.cardId = cardId;
        this.price = price;
        this.source = source;
        this.capturedAt = capturedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCardId() {
        return cardId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getCapturedAt() {
        return capturedAt;
    }
}
