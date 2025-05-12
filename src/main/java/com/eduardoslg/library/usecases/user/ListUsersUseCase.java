package com.eduardoslg.library.usecases.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eduardoslg.library.entities.User;
import com.eduardoslg.library.repositories.UserRepository;

@Component
public class ListUsersUseCase {
  private final UserRepository userRepository;

  public ListUsersUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> execute() {
    return userRepository.findAll();
  }
}
