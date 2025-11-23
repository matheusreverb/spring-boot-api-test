package com.example.meu_projeto_web.application.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.meu_projeto_web.domain.entities.User;
import com.example.meu_projeto_web.domain.exceptions.ValidationException;
import com.example.meu_projeto_web.domain.repositories.UserRepository;
import com.example.meu_projeto_web.infrastructure.controllers.dto.CreateUserRequest;

@Service
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public void createUser(CreateUserRequest request) {

    if (repository.emailExists(request.getEmail())) {
      throw new ValidationException("email", "Email já cadastrado");
    }

    if (repository.phoneExists(request.getPhone())) {
      throw new ValidationException("phone", "Telefone já cadastrado");
    }

    LocalDate birthDateFormatted = LocalDate.parse(request.getBirthDate(), FORMATTER);

    User user = new User(
        request.getName(),
        request.getEmail(),
        request.getPassword(),
        birthDateFormatted,
        request.getPhone());

    String hashedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(hashedPassword);

    repository.create(user);
  }

  // public void updateUser(...) { ... }
  // public void deleteUser(...) { ... }

  public Optional<User> findUserByEmail(String email) {
    return repository.findByEmail(email);
  }

  private String extractField(String message) {
    Pattern pattern = Pattern.compile("Chave \\((.*?)\\)=");
    Matcher matcher = pattern.matcher(message);

    if (matcher.find()) {
      return matcher.group(1);
    }

    return "campo desconhecido";
  }
}