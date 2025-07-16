package br.com.alura.challenge_forum_hub.domain.repository;

import br.com.alura.challenge_forum_hub.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
