package br.com.alura.challenge_forum_hub.dto.topico;

import java.time.LocalDateTime;

public record DadosResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        Boolean solucao
) {}
