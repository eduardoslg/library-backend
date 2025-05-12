package com.eduardoslg.library.usecases.user;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.eduardoslg.library.dtos.users.UserSessionDTO;
import com.eduardoslg.library.dtos.users.UserSessionResponseDTO;
import com.eduardoslg.library.entities.User;
import com.eduardoslg.library.errors.AppError;
import com.eduardoslg.library.repositories.UserRepository;
import com.eduardoslg.library.utils.JwtUtils;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class AuthenticateUserUseCase {
      private final UserRepository userRepository;

  public AuthenticateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserSessionResponseDTO execute(UserSessionDTO dto) {
    Optional<User> userExists = userRepository.findByEmail(dto.getEmail());

    if (userExists.isEmpty()) throw new AppError("Usuário ou senha inválidos.");

    User user = userExists.get();

    BCrypt.Result passwordMatch = BCrypt.verifyer().verify(dto.getPassword().toCharArray(), user.getPassword());

    if (!passwordMatch.verified) throw new AppError("Usuário ou senha inválidos.");

    String token = JwtUtils.generateToken(user.getId().toString());

    return new UserSessionResponseDTO(token, user.getId(), user.getName(), user.getEmail());
  }
}
