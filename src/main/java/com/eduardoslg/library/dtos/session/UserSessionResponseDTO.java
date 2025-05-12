package com.eduardoslg.library.dtos.session;

public record UserSessionResponseDTO(String token, Long id, String name, String email) {}
