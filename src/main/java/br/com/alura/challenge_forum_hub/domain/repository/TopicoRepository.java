package br.com.alura.challenge_forum_hub.domain.repository;

import br.com.alura.challenge_forum_hub.domain.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}