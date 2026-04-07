package com.erik.pokemonportfolio.collection.api.controller;

import com.erik.pokemonportfolio.collection.api.dto.*;
import com.erik.pokemonportfolio.collection.application.usecase.*;
import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.model.CollectionItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CreateCollectionUseCase createCollectionUseCase;
    private final GetCollectionByIdUseCase getCollectionByIdUseCase;
    private final AddItemToCollectionUseCase addItemToCollectionUseCase;
    private final RenameCollectionUseCase renameCollectionUseCase;
    private final RemoveItemFromCollectionUseCase removeItemFromCollectionUseCase;
    private final GetCollectionsByUserIdUseCase getCollectionsByUserIdUseCase;

    public CollectionController(
            CreateCollectionUseCase createCollectionUseCase,
            GetCollectionByIdUseCase getCollectionByIdUseCase,
            AddItemToCollectionUseCase addItemToCollectionUseCase,
            RenameCollectionUseCase renameCollectionUseCase,
            RemoveItemFromCollectionUseCase removeItemFromCollectionUseCase,
            GetCollectionsByUserIdUseCase getCollectionsByUserIdUseCase
    ) {
        this.createCollectionUseCase = createCollectionUseCase;
        this.getCollectionByIdUseCase = getCollectionByIdUseCase;
        this.addItemToCollectionUseCase = addItemToCollectionUseCase;
        this.renameCollectionUseCase = renameCollectionUseCase;
        this.removeItemFromCollectionUseCase = removeItemFromCollectionUseCase;
        this.getCollectionsByUserIdUseCase = getCollectionsByUserIdUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectionResponse> create(
            @AuthenticationPrincipal String userId,
            @RequestBody CreateCollectionRequest request) {
        Collection collection = createCollectionUseCase.execute(
                UUID.fromString(userId),
                request.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toResponse(collection));
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<CollectionResponse> getById(
            @PathVariable UUID collectionId,
            @AuthenticationPrincipal String userId
    ) {
        Collection collection = getCollectionByIdUseCase.execute(collectionId, UUID.fromString(userId));

        return ResponseEntity.ok(toResponse(collection));
    }

    @PostMapping("/{collectionId}/items")
    public ResponseEntity<CollectionResponse> addItem(
            @PathVariable UUID collectionId,
            @AuthenticationPrincipal String userId,
            @RequestBody AddCollectionItemRequest request
    ) {
        Collection collection = addItemToCollectionUseCase.execute(
                collectionId,
                UUID.fromString(userId),
                request.getCardId(),
                request.getQuantity(),
                request.getCondition()
        );

        return ResponseEntity.ok(toResponse(collection));
    }

    @PatchMapping("/{collectionId}")
    public ResponseEntity<CollectionResponse> rename(
            @PathVariable UUID collectionId,
            @AuthenticationPrincipal String userId,
            @RequestBody RenameCollectionRequest request
    ) {
        Collection collection = renameCollectionUseCase.execute(
                collectionId,
                UUID.fromString(userId),
                request.getName()
        );

        return ResponseEntity.ok(toResponse(collection));
    }

    @DeleteMapping("/{collectionId}/items/{itemId}")
    public ResponseEntity<CollectionResponse> removeItem(
            @PathVariable UUID collectionId,
            @PathVariable UUID itemId,
            @AuthenticationPrincipal String userId
    ) {
        Collection collection = removeItemFromCollectionUseCase.execute(
                collectionId,
                UUID.fromString(userId),
                itemId
        );

        return ResponseEntity.ok(toResponse(collection));
    }

    @GetMapping
    public ResponseEntity<List<CollectionResponse>> getAllById(
            @AuthenticationPrincipal String userId
    ) {
        List<CollectionResponse> responses = getCollectionsByUserIdUseCase.execute(UUID.fromString(userId))
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    private CollectionResponse toResponse(Collection collection) {
        List<CollectionItemResponse> items = collection.getItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        return new CollectionResponse(
                collection.getId(),
                collection.getUserId(),
                collection.getName(),
                items
        );
    }

    private CollectionItemResponse toItemResponse(CollectionItem item) {
        return new CollectionItemResponse(
                item.getId(),
                item.getCardId(),
                item.getQuantity(),
                item.getCondition()
        );
    }
}
