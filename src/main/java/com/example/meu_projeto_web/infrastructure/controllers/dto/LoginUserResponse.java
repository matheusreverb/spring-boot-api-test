package com.example.meu_projeto_web.infrastructure.controllers.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserResponse {
  private String token;
  private String name;
  private String email;
  private String phone;
  private LocalDate birthDate;
  private String id;
}
