package com.example.meu_projeto_web.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.meu_projeto_web.application.services.UserService;
import com.example.meu_projeto_web.infrastructure.controllers.dto.CreateUserRequest;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<Void> postUser(@RequestBody CreateUserRequest request) {

    userService.createUser(request);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}