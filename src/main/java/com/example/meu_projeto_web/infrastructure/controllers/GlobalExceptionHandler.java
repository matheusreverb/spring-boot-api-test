package com.example.meu_projeto_web.infrastructure.controllers;

import com.example.meu_projeto_web.domain.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<FieldErrorResponse> handleDomainError(ValidationException ex) {

    FieldErrorResponse erro = new FieldErrorResponse(
        ex.getField(),
        ex.getMessage());

    return ResponseEntity.badRequest().body(erro);
  }

  public record FieldErrorResponse(String field, String message) {
  }
}