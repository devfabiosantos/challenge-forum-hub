package br.com.alura.challenge_forum_hub.dto.topico;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalheTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        String status,
        List<DadosResposta> respostas
) {}
