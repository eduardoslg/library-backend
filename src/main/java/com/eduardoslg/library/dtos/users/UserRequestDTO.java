package com.eduardoslg.library.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "É necessário informar o campo 'name'")
    @Size(max = 255, message = "O campo 'name' deve ter no máximo 255 caracteres.")
    private String name;

    @NotBlank(message = "É necessário informar o campo 'email'")
    @Email(message = "Email inválido.")
    @Size(max = 255, message = "O campo 'email' deve ter no máximo 255 caracteres.")
    private String email;

    @NotBlank(message = "É necessário informar o campo 'password'")
    @Size(max = 60, message = "O campo 'password' deve ter no máximo 60 caracteres.")
    private String password;
}