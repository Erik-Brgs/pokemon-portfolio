package com.erik.pokemonportfolio.collection.domain.exception;

public class CollectionNotFoundException extends RuntimeException {

    public CollectionNotFoundException(String message) {
        super(message);
    }
}
