package br.com.caelum.cursos.adapters.database.jpa.entity;

import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.core.turma.Turno;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "turmas")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class TurmaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    private CursoJpaEntity curso;

    @ManyToOne(fetch = FetchType.LAZY)
    private SalaJpaEntity sala;

    @Enumerated(EnumType.STRING)
    private Turno turno;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public TurmaJpaEntity(Long id, String codigo, CursoJpaEntity curso, SalaJpaEntity sala, Turno turno, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.codigo = codigo;
        this.curso = curso;
        this.sala = sala;
        this.turno = turno;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public TurmaJpaEntity(Turma turma, CursoJpaEntity curso, SalaJpaEntity sala) {
        this(
                null,
                turma.getCodigo(),
                curso,
                sala,
                turma.getTurno(),
                turma.getDataInicio(),
                turma.getDataFim());
    }

}
