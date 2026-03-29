package com.erik.pokemonportfolio.identity.infrastructure.persistence.repository;

import com.erik.pokemonportfolio.identity.domain.model.User;
import com.erik.pokemonportfolio.identity.domain.repository.UserRepository;
import com.erik.pokemonportfolio.identity.infrastructure.persistence.entity.UserJpaEntity;

import java.util.Optional;
import java.util.UUID;

public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public  JpaUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = springDataUserRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springDataUserRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springDataUserRepository.findByEmail(email)
                .map(this::toDomain);
    }

    private UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getPasswordHash(),
                user.getCreatedAt()
        );
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getDisplayName(),
                entity.getPasswordHash(),
                entity.getCreatedAt()
        );
    }
}
