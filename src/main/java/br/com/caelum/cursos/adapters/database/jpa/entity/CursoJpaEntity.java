package br.com.caelum.cursos.adapters.database.jpa.entity;

import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.curso.Nivel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Entity
@Table(name = "cursos")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class CursoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    private Duration duracaoEmHoras;

    public CursoJpaEntity(Long id, String codigo, String nome, Nivel nivel, Duration duracaoEmHoras) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.nivel = nivel;
        this.duracaoEmHoras = duracaoEmHoras;
    }

    public CursoJpaEntity(Curso curso) {
        this(null, curso.getCodigo(), curso.getNome(), curso.getNivel(), curso.getDuracaoEmHoras());
    }

}
