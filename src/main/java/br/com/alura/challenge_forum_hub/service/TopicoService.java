package br.com.alura.challenge_forum_hub.service;

import br.com.alura.challenge_forum_hub.domain.model.Curso;
import br.com.alura.challenge_forum_hub.domain.model.Topico;
import br.com.alura.challenge_forum_hub.domain.model.Usuario;
import br.com.alura.challenge_forum_hub.domain.repository.CursoRepository;
import br.com.alura.challenge_forum_hub.domain.repository.TopicoRepository;
import br.com.alura.challenge_forum_hub.domain.repository.UsuarioRepository;
import br.com.alura.challenge_forum_hub.dto.topico.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public List<DadosListagemTopico> listarTodos() {
        return topicoRepository.findAll()
                .stream()
                .map(topico -> new DadosListagemTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getDataCriacao(),
                        topico.getStatus(),
                        topico.getAutor().getNome(),
                        topico.getCurso().getNome()
                ))
                .toList();
    }

    public DadosDetalheTopico listarPorId(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        return new DadosDetalheTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                topico.getStatus(),
                topico.getRespostas().stream()
                        .map(r -> new DadosResposta(
                                r.getId(),
                                r.getMensagem(),
                                r.getDataCriacao(),
                                r.getAutor().getNome(),
                                r.getSolucao()
                        ))
                        .toList()
        );
    }

    public DadosDetalheTopico criar(DadosNovoTopico dados, UserDetails userDetails) {
        Usuario autor = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Curso curso = cursoRepository.findByNome(dados.nomeCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Verifica duplicidade
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (existeDuplicado) {
            throw new RuntimeException("Já existe um tópico com o mesmo título e mensagem");
        }

        Topico novoTopico = Topico.builder()
                .titulo(dados.titulo())
                .mensagem(dados.mensagem())
                .dataCriacao(LocalDateTime.now())
                .status("ABERTO")
                .autor(autor)
                .curso(curso)
                .build();

        var salvo = topicoRepository.save(novoTopico);

        return new DadosDetalheTopico(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getMensagem(),
                salvo.getDataCriacao(),
                salvo.getAutor().getNome(),
                salvo.getCurso().getNome(),
                salvo.getStatus(),
                List.of()
        );
    }

    public DadosTopicoAtualizado atualizar(Long id, DadosAtualizaTopico dados, UserDetails userDetails) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        if (!topico.getAutor().getEmail().equals(userDetails.getUsername())) {
            throw new RuntimeException("Sem permissão para editar este tópico");
        }

        // Verifica duplicidade ignorando o próprio
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());
        boolean mudouConteudo = !topico.getTitulo().equals(dados.titulo()) || !topico.getMensagem().equals(dados.mensagem());

        if (existeDuplicado && mudouConteudo) {
            throw new RuntimeException("Já existe outro tópico com o mesmo título e mensagem");
        }

        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        var atualizado = topicoRepository.save(topico);

        return new DadosTopicoAtualizado(
                atualizado.getId(),
                atualizado.getTitulo(),
                atualizado.getMensagem(),
                atualizado.getDataCriacao()
        );
    }

    public void deletar(Long id, UserDetails userDetails) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        if (!topico.getAutor().getEmail().equals(userDetails.getUsername())) {
            throw new RuntimeException("Sem permissão para excluir este tópico");
        }

        topicoRepository.delete(topico);
    }
}
