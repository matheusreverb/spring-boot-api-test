package com.example.meu_projeto_web.domain.repositories;

import java.util.Optional;

import com.example.meu_projeto_web.domain.entities.User;

public interface UserRepository {
  void create(User user);

  Boolean emailExists(String email);

  Boolean phoneExists(String phone);

  Optional<User> findByEmail(String email);

}
