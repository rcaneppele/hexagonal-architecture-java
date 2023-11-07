package br.com.caelum.cursos.adapters.database.jpa.entity;

import br.com.caelum.cursos.domain.core.sala.Sala;
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

    public SalaJpaEntity(Long id, String nome, Integer capacidade) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public SalaJpaEntity(Sala sala) {
        this(null, sala.getNome(), sala.getCapacidade());
    }

}
