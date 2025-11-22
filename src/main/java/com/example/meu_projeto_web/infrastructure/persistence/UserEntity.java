package com.example.meu_projeto_web.infrastructure.persistence;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @UuidGenerator
  @Column(nullable = false, unique = true, updatable = false)
  private String uuid;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 100, unique = true)
  private String email;

  @Column(nullable = false, length = 60)
  private String password;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false, length = 15, unique = true)
  private String phone;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public UserEntity(String name, String email, String password, LocalDate birthDate, String phone) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.birthDate = birthDate;
    this.phone = phone;
  }
}
