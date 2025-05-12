package com.eduardoslg.library.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardoslg.library.dtos.users.UserRequestDTO;
import com.eduardoslg.library.dtos.users.UserResponseDTO;
import com.eduardoslg.library.dtos.users.UserSessionDTO;
import com.eduardoslg.library.dtos.users.UserSessionResponseDTO;
import com.eduardoslg.library.entities.User;
import com.eduardoslg.library.usecases.user.AuthenticateUserUseCase;
import com.eduardoslg.library.usecases.user.CreateUserUseCase;
import com.eduardoslg.library.usecases.user.ListUsersUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO user = createUserUseCase.execute(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> list() {
        return ResponseEntity.ok(
            listUsersUseCase.execute().stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail()))
            .toList()
    );
    }

    @PostMapping("/session")
    public ResponseEntity<?> session(@RequestBody @Valid UserSessionDTO dto) {
        UserSessionResponseDTO user = authenticateUserUseCase.execute(dto);
        
        return ResponseEntity.ok(user);
    }
    
}
