package br.com.alura.challenge_forum_hub.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private Boolean solucao = false;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
