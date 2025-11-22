package com.example.meu_projeto_web.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import com.example.meu_projeto_web.domain.entities.User;
import com.example.meu_projeto_web.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UserJpaRepository userJpaRepository;

  public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
    this.userJpaRepository = userJpaRepository;
  }

  @Override
  public Boolean emailExists(String email) {
    return userJpaRepository.existsByEmail(email);
  }

  @Override
  public Boolean phoneExists(String phone) {
    return userJpaRepository.existsByPhone(phone);
  }

  @Override
  public void create(User user) {
    UserEntity userEntity = new UserEntity(
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getBirthDate(),
        user.getPhone());

    userJpaRepository.save(userEntity);
  }
}
