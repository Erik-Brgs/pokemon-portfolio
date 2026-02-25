package com.erik.pokemonportfolio.catalog.api.controller;

import com.erik.pokemonportfolio.catalog.api.dto.CardResponse;
import com.erik.pokemonportfolio.catalog.api.dto.RegisterCardRequest;
import com.erik.pokemonportfolio.catalog.application.usecase.RegisterCardUseCase;
import com.erik.pokemonportfolio.catalog.domain.model.Card;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final RegisterCardUseCase registerCardUseCase;

    public CardController(RegisterCardUseCase registerCardUseCase) {
        this.registerCardUseCase = registerCardUseCase;
    }

    @PostMapping
    public ResponseEntity<CardResponse> registerCard(@Valid @RequestBody RegisterCardRequest registerCardRequest) {
        Card card = registerCardUseCase.execute(
                registerCardRequest.getExternalId(),
                registerCardRequest.getName(),
                registerCardRequest.getSetName(),
                registerCardRequest.getLanguage(),
                registerCardRequest.getRarity()
        );

        CardResponse response = new CardResponse(
                card.getId(),
                card.getExternalId(),
                card.getName(),
                card.getSetName(),
                card.getLanguage(),
                card.getRarity()
        );

        return ResponseEntity.ok(response);
    }
}
