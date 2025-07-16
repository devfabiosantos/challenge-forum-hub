package br.com.alura.challenge_forum_hub.controller;

import br.com.alura.challenge_forum_hub.domain.model.Usuario;
import br.com.alura.challenge_forum_hub.dto.UsuarioDTO;
import br.com.alura.challenge_forum_hub.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        Usuario novoUsuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok(novoUsuario);
    }
}
