package com.example.meu_projeto_web.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meu_projeto_web.application.services.AuthService;
import com.example.meu_projeto_web.infrastructure.controllers.dto.LoginUserRequest;
import com.example.meu_projeto_web.infrastructure.controllers.dto.LoginUserResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
    LoginUserResponse response = authService.login(request);
    return ResponseEntity.ok(response);
  }
}