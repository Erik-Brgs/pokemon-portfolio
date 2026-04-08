package com.erik.pokemonportfolio.pricing.api.controller;

import com.erik.pokemonportfolio.pricing.api.dto.PriceSnapshotResponse;
import com.erik.pokemonportfolio.pricing.api.dto.RegisterPriceSnapshotRequest;
import com.erik.pokemonportfolio.pricing.application.usecase.GetLatestPriceByCardIdUseCase;
import com.erik.pokemonportfolio.pricing.application.usecase.GetPriceHistoryByCardIdUseCase;
import com.erik.pokemonportfolio.pricing.application.usecase.RegisterPriceSnapshotUseCase;
import com.erik.pokemonportfolio.pricing.domain.model.PriceSnapshot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
    @RequestMapping("/prices")
public class PriceSnapshotController {

    private final RegisterPriceSnapshotUseCase registerPriceSnapshotUseCase;
    private final GetLatestPriceByCardIdUseCase getLatestPriceByCardIdUseCase;
    private final GetPriceHistoryByCardIdUseCase getPriceHistoryByCardIdUseCase;

    public PriceSnapshotController(
            RegisterPriceSnapshotUseCase registerPriceSnapshotUseCase,
            GetLatestPriceByCardIdUseCase getLatestPriceByCardIdUseCase,
            GetPriceHistoryByCardIdUseCase getPriceHistoryByCardIdUseCase
    ) {
        this.registerPriceSnapshotUseCase = registerPriceSnapshotUseCase;
        this.getLatestPriceByCardIdUseCase = getLatestPriceByCardIdUseCase;
        this.getPriceHistoryByCardIdUseCase = getPriceHistoryByCardIdUseCase;
    }

    @PostMapping
    public ResponseEntity<PriceSnapshotResponse> register(
            @RequestBody RegisterPriceSnapshotRequest request
    ) {
        PriceSnapshot snapshot = registerPriceSnapshotUseCase.execute(
                request.getCardId(),
                request.getPrice(),
                request.getSource()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(snapshot));
    }

    @GetMapping("/cards/{cardId}/latest")
    public ResponseEntity<PriceSnapshotResponse> getLatest(
            @PathVariable UUID cardId
    ) {
        PriceSnapshot snapshot = getLatestPriceByCardIdUseCase.execute(cardId);
        return ResponseEntity.ok(toResponse(snapshot));
    }

    @GetMapping("/cards/{cardId}/history")
    public ResponseEntity<List<PriceSnapshotResponse>> getHistory(
            @PathVariable UUID cardId
    ) {
        List<PriceSnapshotResponse> responses = getPriceHistoryByCardIdUseCase.execute(cardId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    private PriceSnapshotResponse toResponse(PriceSnapshot snapshot) {
        return new PriceSnapshotResponse(
                snapshot.getId(),
                snapshot.getCardId(),
                snapshot.getPrice(),
                snapshot.getSource(),
                snapshot.getCapturedAt()
        );
    }
}
