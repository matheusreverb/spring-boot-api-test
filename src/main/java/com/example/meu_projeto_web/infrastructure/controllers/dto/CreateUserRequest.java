package com.example.meu_projeto_web.infrastructure.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
  private String name;
  private String email;
  private String password;
  private String birthDate;
  private String phone;
}
