package com.example.meu_projeto_web.domain.enums;

public enum UserValidationParams {
  NAME("name"),
  EMAIL("email"),
  PASSWORD("password"),
  BIRTH_DATE("birthDate"),
  PHONE("phone"),
  ROOT("root");

  private final String field;

  UserValidationParams(String field) {
    this.field = field;
  }

  public String getField() {
    return field;
  }
}
