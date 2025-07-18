package br.com.alura.challenge_forum_hub.dto.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosNovoTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String nomeCurso
) {}
