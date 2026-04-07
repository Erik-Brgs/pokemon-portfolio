package com.erik.pokemonportfolio.pricing.infrastructure.config;

import com.erik.pokemonportfolio.pricing.application.usecase.GetLatestPriceByCardIdUseCase;
import com.erik.pokemonportfolio.pricing.application.usecase.GetPriceHistoryByCardIdUseCase;
import com.erik.pokemonportfolio.pricing.application.usecase.RegisterPriceSnapshotUseCase;
import com.erik.pokemonportfolio.pricing.domain.repository.PriceSnapshotRepository;
import com.erik.pokemonportfolio.pricing.infrastructure.persistente.repository.JpaPriceSnapshotRepository;
import com.erik.pokemonportfolio.pricing.infrastructure.persistente.repository.SpringDataPriceSnapshotRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PricingConfiguration {

    @Bean
    public JpaPriceSnapshotRepository jpaPriceSnapshotRepository(
            SpringDataPriceSnapshotRepository springDataPriceSnapshotRepository
    ) {
        return new JpaPriceSnapshotRepository(springDataPriceSnapshotRepository);
    }

    @Bean
    public PriceSnapshotRepository priceSnapshotRepository(
            JpaPriceSnapshotRepository jpaPriceSnapshotRepository
    ) {
        return jpaPriceSnapshotRepository;
    }

    @Bean
    public RegisterPriceSnapshotUseCase registerPriceSnapshotUseCase(
            PriceSnapshotRepository priceSnapshotRepository
    ) {
        return new RegisterPriceSnapshotUseCase(priceSnapshotRepository);
    }

    @Bean
    public GetLatestPriceByCardIdUseCase getLatestPriceByCardIdUseCase(
            PriceSnapshotRepository priceSnapshotRepository
    ) {
        return new GetLatestPriceByCardIdUseCase(priceSnapshotRepository);
    }

    @Bean
    public GetPriceHistoryByCardIdUseCase getPriceHistoryByCardIdUseCase(
            PriceSnapshotRepository priceSnapshotRepository
    ) {
        return new GetPriceHistoryByCardIdUseCase(priceSnapshotRepository);
    }
}
