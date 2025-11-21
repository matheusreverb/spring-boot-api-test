package com.example.meu_projeto_web.infrastructure.controllers;

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

  @PostMapping
  public String createUser(@RequestBody CreateUserRequest request) {
    userService.createUser(
        request.getName(),
        request.getEmail(),
        request.getPassword(),
        request.getBirthDate(),
        request.getPhone());

    return "User created successfully";
  }
}