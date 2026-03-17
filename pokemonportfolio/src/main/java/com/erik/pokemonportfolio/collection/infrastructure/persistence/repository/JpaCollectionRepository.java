package com.erik.pokemonportfolio.collection.infrastructure.persistence.repository;

import com.erik.pokemonportfolio.collection.domain.model.Collection;
import com.erik.pokemonportfolio.collection.domain.model.CollectionItem;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;
import com.erik.pokemonportfolio.collection.infrastructure.persistence.entity.CollectionItemJpaEntity;
import com.erik.pokemonportfolio.collection.infrastructure.persistence.entity.CollectionJpaEntity;

import java.util.List;
import java.util.Optional;

public class JpaCollectionRepository implements CollectionRepository {

    private final SpringDataCollectionRepository springDataCollectionRepository;

    public JpaCollectionRepository(SpringDataCollectionRepository springDataCollectionRepository) {
        this.springDataCollectionRepository = springDataCollectionRepository;
    }

    @Override
    public Collection save(Collection collection) {
        CollectionJpaEntity entity = toEntity(collection);
        CollectionJpaEntity savedEntity = springDataCollectionRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Collection> findById(java.util.UUID id) {
        return springDataCollectionRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public void delete(Collection collection) {
        springDataCollectionRepository.deleteById(collection.getId());
    }

    private CollectionJpaEntity toEntity(Collection collection) {
        CollectionJpaEntity entity = new CollectionJpaEntity(
                collection.getId(),
                collection.getUserId(),
                collection.getName(),
                collection.getCreatedAt()
        );

        List<CollectionItemJpaEntity> itemEntities = collection.getItems().stream()
                .map(this::toItemEntity)
                .toList();

        entity.setItems(itemEntities);

        return entity;
    }

    private CollectionItemJpaEntity toItemEntity(CollectionItem item) {
        return new CollectionItemJpaEntity(
                item.getId(),
                item.getCardId(),
                item.getQuantity(),
                item.getCondition(),
                item.getCreatedAt()
        );
    }

    private Collection toDomain(CollectionJpaEntity entity) {
        List<CollectionItem> items = entity.getItems().stream()
                .map(this::toItemDomain)
                .toList();

        return new Collection(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getCreatedAt(),
                items
        );
    }

    private CollectionItem toItemDomain(CollectionItemJpaEntity entity) {
        return new CollectionItem(
                entity.getId(),
                entity.getCardId(),
                entity.getQuantity(),
                entity.getCondition(),
                entity.getCreatedAt()
        );
    }
}
