package com.erik.pokemonportfolio.catalog.domain.exception;

public class CardAlreadyExistsException extends RuntimeException{

    public CardAlreadyExistsException(String externalId) {
        super("Card with externalId '" + externalId + "' already exists.");
    }
}
