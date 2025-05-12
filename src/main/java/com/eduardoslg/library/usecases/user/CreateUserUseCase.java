package com.eduardoslg.library.usecases.user;

import java.util.Optional;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.eduardoslg.library.dtos.users.UserRequestDTO;
import com.eduardoslg.library.dtos.users.UserResponseDTO;
import com.eduardoslg.library.entities.User;
import com.eduardoslg.library.errors.AppError;
import com.eduardoslg.library.repositories.UserRepository;

@Component
public class CreateUserUseCase {
  private final UserRepository userRepository;

  public CreateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponseDTO execute(UserRequestDTO dto) {

    Optional<User> userExists = userRepository.findByEmail(dto.getEmail());

    if (!userExists.isEmpty()) throw new AppError("Já existe um usuário com o email informado.");

    String passwordHash = BCrypt.withDefaults()
      .hashToString(12, dto.getPassword().toCharArray());

    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(passwordHash);
    return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
  }
}