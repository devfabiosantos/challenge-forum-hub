package br.com.alura.challenge_forum_hub.service;

import br.com.alura.challenge_forum_hub.domain.model.Usuario;
import br.com.alura.challenge_forum_hub.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPerfis().stream()
                        .map(perfil -> new SimpleGrantedAuthority(perfil.getNome()))
                        .collect(Collectors.toList())
        );
    }
}
