package com.example.meu_projeto_web.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Boolean existsByEmail(String email);

  Boolean existsByPhone(String phone);
}
