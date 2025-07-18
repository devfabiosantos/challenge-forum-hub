package br.com.alura.challenge_forum_hub.dto.topico;

import java.time.LocalDateTime;

public record DadosTopicoAtualizado(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao
) {}
