package br.com.alura.challenge_forum_hub.service;

import br.com.alura.challenge_forum_hub.domain.model.Perfil;
import br.com.alura.challenge_forum_hub.domain.model.Usuario;
import br.com.alura.challenge_forum_hub.domain.repository.PerfilRepository;
import br.com.alura.challenge_forum_hub.domain.repository.UsuarioRepository;
import br.com.alura.challenge_forum_hub.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario cadastrarUsuario(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .build();

        // Opcional: atribui perfil padrão ROLE_USER
        Perfil perfilUser = perfilRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Perfil padrão não encontrado"));

        Set<Perfil> perfis = new HashSet<>();
        perfis.add(perfilUser);
        usuario.setPerfis(perfis);

        return usuarioRepository.save(usuario);
    }
}
