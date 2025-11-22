package com.example.meu_projeto_web.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.meu_projeto_web.domain.exceptions.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<FieldErrorResponse> handleDomainError(ValidationException ex) {

    FieldErrorResponse erro = new FieldErrorResponse(
        ex.getField(),
        ex.getMessage());

    return ResponseEntity.badRequest().body(erro);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<FieldErrorResponse> handleUnexpected(RuntimeException ex) {
    FieldErrorResponse erro = new FieldErrorResponse("internal", ex.getMessage());
    return ResponseEntity.status(500).body(erro);
  }

  public record FieldErrorResponse(String field, String message) {
  }
}