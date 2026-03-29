package com.erik.pokemonportfolio.collection.domain.exception;

public class CollectionAccessDeniedException extends RuntimeException {

    public CollectionAccessDeniedException(String message) {
        super(message);
    }
}
