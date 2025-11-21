package com.example.meu_projeto_web.application.services;

import org.springframework.stereotype.Service;

import com.example.meu_projeto_web.domain.entities.User;
import com.example.meu_projeto_web.domain.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public void createUser(String name, String email, String password, String birthDate, String phone) {
    User user = new User(name, email, password, birthDate, phone);
    repository.create(user);
  }

  // Futuramente você colocará aqui:
  // public void updateUser(...) { ... }
  // public void deleteUser(...) { ... }
}