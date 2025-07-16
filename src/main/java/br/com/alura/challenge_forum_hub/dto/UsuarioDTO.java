package br.com.alura.challenge_forum_hub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha
) {}
