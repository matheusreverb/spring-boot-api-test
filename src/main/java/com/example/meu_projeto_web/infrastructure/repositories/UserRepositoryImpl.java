package com.example.meu_projeto_web.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import com.example.meu_projeto_web.domain.entities.User;
import com.example.meu_projeto_web.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Override
  public void create(User user) {
    System.out.println("User created: " + user);
  }
}
