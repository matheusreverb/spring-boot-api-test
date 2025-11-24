package com.example.meu_projeto_web.infrastructure.controllers.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUserResponse {
  private String token;
  private String name;
  private String email;
  private String phone;
  private LocalDate birthDate;
  private String id;
}
