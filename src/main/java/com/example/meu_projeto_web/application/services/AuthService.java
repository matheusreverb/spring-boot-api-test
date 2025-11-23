package com.example.meu_projeto_web.application.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.meu_projeto_web.domain.entities.User;
import com.example.meu_projeto_web.domain.enums.UserValidationParams;
import com.example.meu_projeto_web.domain.exceptions.ValidationException; // Ou InvalidCredentialsException
import com.example.meu_projeto_web.domain.repositories.UserRepository;
import com.example.meu_projeto_web.infrastructure.controllers.dto.LoginUserRequest;
import com.example.meu_projeto_web.infrastructure.controllers.dto.LoginUserResponse;
import com.example.meu_projeto_web.infrastructure.security.TokenProvider;

@Service
public class AuthService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  public LoginUserResponse login(LoginUserRequest request) {
    User user = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new ValidationException(UserValidationParams.ROOT.getField(), "Email ou senha incorretos"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new ValidationException(UserValidationParams.ROOT.getField(), "Email ou senha incorretos");
    }

    String token = tokenProvider.generateToken(user);

    return new LoginUserResponse(
        token,
        user.getName(),
        user.getEmail(),
        user.getPhone(),
        user.getBirthDate(),
        user.getId());
  }
}