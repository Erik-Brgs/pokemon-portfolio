package com.erik.pokemonportfolio.pricing.domain.exception;

public class PriceSnapshotNotFoundException extends RuntimeException {

    public PriceSnapshotNotFoundException(String message) {
        super(message);
    }
}
