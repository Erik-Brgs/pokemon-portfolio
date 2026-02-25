package com.erik.pokemonportfolio.catalog.infrastructure.config;

import com.erik.pokemonportfolio.catalog.application.usecase.GetCardByIdUseCase;
import com.erik.pokemonportfolio.catalog.application.usecase.RegisterCardUseCase;
import com.erik.pokemonportfolio.catalog.domain.repository.CardRepository;
import com.erik.pokemonportfolio.catalog.infrastructure.persistence.repository.JpaCardRepository;
import com.erik.pokemonportfolio.catalog.infrastructure.persistence.repository.SpringDataCardRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfiguration {

    @Bean
    public CardRepository cardRepository(SpringDataCardRepository springDataRepository) {
        return new JpaCardRepository(springDataRepository);
    }

    @Bean
    public RegisterCardUseCase registerCardUseCase(CardRepository cardRepository) {
        return new RegisterCardUseCase(cardRepository);
    }

    @Bean
    public GetCardByIdUseCase getCardByIdUseCase(CardRepository cardRepository) {
        return new GetCardByIdUseCase(cardRepository);
    }
}
