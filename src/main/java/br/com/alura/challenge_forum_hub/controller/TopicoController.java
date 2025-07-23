package br.com.alura.challenge_forum_hub.controller;

import br.com.alura.challenge_forum_hub.dto.topico.*;
import br.com.alura.challenge_forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<DadosListagemTopico>> listarTodos() {
        var lista = topicoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalheTopico> listarPorId(@PathVariable Long id) {
        var detalhe = topicoService.listarPorId(id);
        return ResponseEntity.ok(detalhe);
    }

    @PostMapping
    public ResponseEntity<DadosDetalheTopico> criar(
            @RequestBody @Valid DadosNovoTopico dados,
            @AuthenticationPrincipal UserDetails userDetails) {
        var criado = topicoService.criar(dados, userDetails);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosTopicoAtualizado> atualizar(
            @PathVariable Long id,
            @RequestBody DadosAtualizaTopico dados,
            @AuthenticationPrincipal UserDetails userDetails) {
        var atualizado = topicoService.atualizar(id, dados, userDetails);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        topicoService.deletar(id, userDetails);
        return ResponseEntity.ok().build();
    }
}
