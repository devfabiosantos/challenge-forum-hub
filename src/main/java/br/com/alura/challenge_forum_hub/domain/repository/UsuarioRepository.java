package br.com.alura.challenge_forum_hub.domain.repository;

import br.com.alura.challenge_forum_hub.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
