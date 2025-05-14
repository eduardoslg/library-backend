package com.eduardoslg.library.usecases.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.eduardoslg.library.dtos.users.UpdateUserRequest;
import com.eduardoslg.library.dtos.users.UserRequestDTO;
import com.eduardoslg.library.dtos.users.UserResponseDTO;
import com.eduardoslg.library.entities.User;
import com.eduardoslg.library.errors.AppError;
import com.eduardoslg.library.repositories.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.EntityNotFoundException;

@Component
public class UpdateUserUseCase {
      private final UserRepository userRepository;

  public UpdateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserResponseDTO execute(Long id, UpdateUserRequest updateUserRequest) {

    User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado com o ID informado."));

    user.setName(updateUserRequest.getName());

    userRepository.save(user);
    return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
  }
}
