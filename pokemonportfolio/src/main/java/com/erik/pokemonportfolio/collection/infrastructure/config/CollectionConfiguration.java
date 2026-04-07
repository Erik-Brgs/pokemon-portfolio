package com.erik.pokemonportfolio.collection.infrastructure.config;

import com.erik.pokemonportfolio.collection.application.usecase.*;
import com.erik.pokemonportfolio.collection.domain.repository.CollectionRepository;
import com.erik.pokemonportfolio.collection.infrastructure.persistence.repository.JpaCollectionRepository;
import com.erik.pokemonportfolio.collection.infrastructure.persistence.repository.SpringDataCollectionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectionConfiguration {

    @Bean
    public JpaCollectionRepository jpaCollectionRepository(
            SpringDataCollectionRepository springDataCollectionRepository
    ) {
        return new JpaCollectionRepository(springDataCollectionRepository);
    }

    @Bean
    public CollectionRepository collectionRepository(JpaCollectionRepository jpaCollectionRepository) {
        return  jpaCollectionRepository;
    }

    @Bean
    public CreateCollectionUseCase createCollectionUseCase(CollectionRepository collectionRepository) {
        return new CreateCollectionUseCase(collectionRepository);
    }

    @Bean
    public GetCollectionByIdUseCase getCollectionByIdUseCase(CollectionRepository collectionRepository) {
        return new GetCollectionByIdUseCase(collectionRepository);
    }

    @Bean
    public AddItemToCollectionUseCase addItemToCollectionUseCase(CollectionRepository collectionRepository) {
        return new AddItemToCollectionUseCase(collectionRepository);
    }

    @Bean
    public RenameCollectionUseCase renameCollectionUseCase(CollectionRepository collectionRepository) {
        return new RenameCollectionUseCase(collectionRepository);
    }

    @Bean
    public RemoveItemFromCollectionUseCase removeItemFromCollectionUseCase(CollectionRepository collectionRepository) {
        return new RemoveItemFromCollectionUseCase(collectionRepository);
    }

    @Bean
    public GetCollectionsByUserIdUseCase getCollectionsByUserIdUseCase(CollectionRepository collectionRepository) {
        return new GetCollectionsByUserIdUseCase(collectionRepository);
    }
}
