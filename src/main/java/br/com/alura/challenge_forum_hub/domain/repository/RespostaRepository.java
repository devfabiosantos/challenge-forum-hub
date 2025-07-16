package br.com.alura.challenge_forum_hub.domain.repository;

import br.com.alura.challenge_forum_hub.domain.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
