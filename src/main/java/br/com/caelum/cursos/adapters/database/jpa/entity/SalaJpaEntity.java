package br.com.caelum.cursos.adapters.database.jpa.entity;

import br.com.caelum.cursos.domain.sala.ports.DadosParaCadastrarSala;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salas")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class SalaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer capacidade;

    public SalaJpaEntity(DadosParaCadastrarSala dados) {
        this.nome = dados.nome();
        this.capacidade = dados.capacidade();
    }

}
