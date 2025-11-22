package com.example.meu_projeto_web.domain.entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import com.example.meu_projeto_web.domain.enums.UserValidationParams;
import com.example.meu_projeto_web.domain.exceptions.ValidationException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
  private String name;
  private String email;
  private String password;
  private LocalDate birthDate;
  private String phone;

  public User(String name, String email, String password, LocalDate birthDate, String phone) {
    validateName(name);
    validateEmail(email);
    validatePassword(password);
    validateBirthDate(birthDate);
    validatePhone(phone);

    this.name = name;
    this.email = email;
    this.password = password;
    this.birthDate = birthDate;
    this.phone = phone;
  }

  private void validateName(String name) {
    rejectIfNull(name, UserValidationParams.NAME, "O campo de nome inválido");
    rejectIfTooShort(name, UserValidationParams.NAME, 3, "Nome deve ter pelo menos 3 caracteres");

    String[] nameParts = name.split(" ");
    if (nameParts.length < 2) {
      throw new ValidationException(UserValidationParams.NAME.getField(), "Nome deve conter nome e sobrenome");
    }
    for (String part : nameParts) {
      if (part.length() < 2) {
        throw new ValidationException(UserValidationParams.NAME.getField(), "Nome deve ter pelo menos 2 caracteres");
      }
    }
  }

  private void validateEmail(String email) {
    rejectIfNull(email, UserValidationParams.EMAIL, "O campo de email é obrigatório");
    if (!email.contains("@") || !email.contains(".")) {
      throw new ValidationException(UserValidationParams.EMAIL.getField(), "Digite um email válido");
    }
  }

  private void validatePassword(String password) {
    rejectIfNull(password, UserValidationParams.PASSWORD, "O campo de senha é obrigatório");
    rejectIfTooShort(password, UserValidationParams.PASSWORD, 8, "É necessário ter ao menos 8 caracteres");

    if (password.contains(" ")) {
      throw new ValidationException(UserValidationParams.PASSWORD.getField(),
          "Sua senha não pode conter espaços em branco");
    }

    if (!password.matches(".*[A-Z].*")) {
      throw new ValidationException(UserValidationParams.PASSWORD.getField(),
          "Sua senha deve conter pelo menos uma letra maiúscula");
    }

    if ((!password.matches(".*[0-9].*"))) {
      throw new ValidationException(UserValidationParams.PASSWORD.getField(),
          "Sua senha deve conter pelo menos um número");
    }

    if (!password.matches(".*[!@#$%^&*()].*")) {
      throw new ValidationException(UserValidationParams.PASSWORD.getField(),
          "Sua senha deve conter pelo menos um caractere especial");
    }
  }

  private void validateBirthDate(LocalDate birthDate) {
    try {
      LocalDate today = LocalDate.now();

      if (birthDate.isAfter((LocalDate.now()))) {
        throw new ValidationException(UserValidationParams.BIRTH_DATE.getField(),
            "Data de nascimento não pode ser no futuro");
      }

      if (birthDate.isBefore((LocalDate.of(1900, 1, 1)))) {
        throw new ValidationException(UserValidationParams.BIRTH_DATE.getField(),
            "Data de nascimento não pode ser antes de 1900");
      }

      int age = Period.between(birthDate, today).getYears();
      if (age < 18) {
        throw new ValidationException(
            UserValidationParams.BIRTH_DATE.getField(),
            "Usuário deve ter pelo menos 18 anos");
      }

    } catch (DateTimeParseException e) {
      throw new ValidationException(UserValidationParams.BIRTH_DATE.getField(), "Data de nascimento inválida");
    }

  }

  private void validatePhone(String phone) {
    rejectIfNull(phone, UserValidationParams.PHONE, "O campo de telefone é obrigatório");
    rejectIfTooShort(phone, UserValidationParams.PHONE, 11, "Telefone inválido");
  }

  private void rejectIfNull(String value, UserValidationParams param, String message) {
    if (value == null || value.trim().isEmpty()) {
      throw new ValidationException(param.getField(), message);
    }
  }

  private void rejectIfTooShort(String value, UserValidationParams param, int minLength, String message) {
    if (value == null || value.trim().length() < minLength) {
      throw new ValidationException(param.getField(), message);
    }
  }
}
