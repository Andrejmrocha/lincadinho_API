package br.com.lincadinho.lincadinho.dto;

import br.com.lincadinho.lincadinho.model.usuario.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrarUsuarioDTO(
        @NotBlank(message = "O nome não pode estar em branco")
                @Size(min = 2, message = "Seu nome deve ter no mínimo duas letras")
        String nome,

        @NotBlank(message = "O email não pode estar em branco")
                @Email(message = "Deve ser um endereço de email válido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
                @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        UserRole role,

        @NotNull(message = "A organização não pode ser nula")
        Long organizacao
) {
}

