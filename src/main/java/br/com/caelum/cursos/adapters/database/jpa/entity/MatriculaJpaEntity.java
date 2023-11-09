package br.com.caelum.cursos.adapters.database.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matriculas")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class MatriculaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    private EstudanteJpaEntity estudante;

    @ManyToOne(fetch = FetchType.LAZY)
    private TurmaJpaEntity turma;

    public MatriculaJpaEntity(Long id, String codigo, EstudanteJpaEntity estudante, TurmaJpaEntity turma) {
        this.id = id;
        this.codigo = codigo;
        this.estudante = estudante;
        this.turma = turma;
    }

    public MatriculaJpaEntity(String codigo, EstudanteJpaEntity estudante, TurmaJpaEntity turma) {
        this(null, codigo, estudante, turma);
    }

}
