package com.eduardoslg.library.dtos.users;

public record UserSessionResponseDTO(String token, Long id, String name, String email) {}
