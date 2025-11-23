package com.example.meu_projeto_web.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Boolean existsByEmail(String email);

  Boolean existsByPhone(String phone);

  Optional<UserEntity> findByEmail(String email);
}
