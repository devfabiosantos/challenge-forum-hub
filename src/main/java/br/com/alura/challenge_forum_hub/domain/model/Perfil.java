package br.com.alura.challenge_forum_hub.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
