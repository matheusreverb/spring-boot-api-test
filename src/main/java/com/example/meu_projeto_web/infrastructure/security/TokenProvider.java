package com.example.meu_projeto_web.infrastructure.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.meu_projeto_web.domain.entities.User;

@Service
public class TokenProvider {

  @Value("${spring.api.token.secret}")
  private String secret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("meu-projeto-web")
          .withSubject(user.getEmail())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao gerar token", e);
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
