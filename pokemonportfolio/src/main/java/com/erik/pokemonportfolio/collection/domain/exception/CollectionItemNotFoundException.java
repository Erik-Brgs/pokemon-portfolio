package com.erik.pokemonportfolio.collection.domain.exception;

public class CollectionItemNotFoundException extends RuntimeException {

    public CollectionItemNotFoundException(String message) {
        super(message);
    }
}
