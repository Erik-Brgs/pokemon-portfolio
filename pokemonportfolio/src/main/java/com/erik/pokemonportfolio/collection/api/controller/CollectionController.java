package com.erik.pokemonportfolio.collection.api.controller;

import com.erik.pokemonportfolio.collection.api.dto.*;
import com.erik.pokemonportfolio.collection.application.usecase.*;
import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.model.CollectionItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public CollectionController(
            CreateCollectionUseCase createCollectionUseCase,
            GetCollectionByIdUseCase getCollectionByIdUseCase,
            AddItemToCollectionUseCase addItemToCollectionUseCase,
            RenameCollectionUseCase renameCollectionUseCase,
            RemoveItemFromCollectionUseCase removeItemFromCollectionUseCase
    ) {
        this.createCollectionUseCase = createCollectionUseCase;
        this.getCollectionByIdUseCase = getCollectionByIdUseCase;
        this.addItemToCollectionUseCase = addItemToCollectionUseCase;
        this.renameCollectionUseCase = renameCollectionUseCase;
        this.removeItemFromCollectionUseCase = removeItemFromCollectionUseCase;
    }

    @PostMapping
    public ResponseEntity<CollectionResponse> create(@RequestBody CreateCollectionRequest request) {
        Collection collection = createCollectionUseCase.execute(
                request.getUserId(),
                request.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toResponse(collection));
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<CollectionResponse> getById(
            @PathVariable UUID collectionId,
            @RequestParam("userId") UUID userid
    ) {
        Collection collection = getCollectionByIdUseCase.execute(collectionId, userid);

        return ResponseEntity.ok(toResponse(collection));
    }

    @PostMapping("/{collectionId}/items")
    public ResponseEntity<CollectionResponse> addItem(
            @PathVariable UUID collectionId,
            @RequestBody AddCollectionItemRequest request
    ) {
        Collection collection = addItemToCollectionUseCase.execute(
                collectionId,
                request.getUserId(),
                request.getCardId(),
                request.getQuantity(),
                request.getCondition()
        );

        return ResponseEntity.ok(toResponse(collection));
    }

    @PatchMapping("/{collectionId}")
    public ResponseEntity<CollectionResponse> rename(
            @PathVariable UUID collectionId,
            @RequestBody RenameCollectionRequest request
    ) {
        Collection collection = renameCollectionUseCase.execute(
                collectionId,
                request.getUserId(),
                request.getName()
        );

        return ResponseEntity.ok(toResponse(collection));
    }

    @DeleteMapping("/{collectionId}/items/{itemId}")
    public ResponseEntity<CollectionResponse> removeItem(
            @PathVariable UUID collectionId,
            @PathVariable UUID itemId,
            @RequestParam("userId") UUID userid
    ) {
        Collection collection = removeItemFromCollectionUseCase.execute(
                collectionId,
                userid,
                itemId
        );

        return ResponseEntity.ok(toResponse(collection));
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
