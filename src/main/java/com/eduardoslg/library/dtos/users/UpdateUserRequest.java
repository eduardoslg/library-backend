package com.eduardoslg.library.dtos.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotBlank(message = "É necessário informar o campo 'name'")
    @Size(max = 255, message = "O campo 'name' deve ter no máximo 255 caracteres.")
    private String name;
}
