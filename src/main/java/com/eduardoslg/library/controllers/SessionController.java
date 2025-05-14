package com.eduardoslg.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardoslg.library.dtos.session.UserSessionDTO;
import com.eduardoslg.library.dtos.session.UserSessionResponseDTO;
import com.eduardoslg.library.usecases.session.AuthenticateUserUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sessions")
public class SessionController {
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping
    public ResponseEntity<?> session(@RequestBody @Valid UserSessionDTO dto) {
        UserSessionResponseDTO user = authenticateUserUseCase.execute(dto);
        return ResponseEntity.ok(user);
    }
}
