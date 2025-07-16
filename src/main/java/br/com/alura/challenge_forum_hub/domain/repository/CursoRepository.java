package br.com.alura.challenge_forum_hub.domain.repository;

import br.com.alura.challenge_forum_hub.domain.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
