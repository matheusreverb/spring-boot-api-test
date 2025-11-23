package com.example.meu_projeto_web.infrastructure.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
  private String email;
  private String password;
}
