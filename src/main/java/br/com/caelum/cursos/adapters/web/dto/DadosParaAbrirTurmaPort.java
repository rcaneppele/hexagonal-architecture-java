package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.CursoJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;
import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.sala.Sala;
import br.com.caelum.cursos.domain.core.turma.Turno;
import br.com.caelum.cursos.domain.ports.turma.DadosParaAbrirTurma;

import java.time.LocalDate;

public record DadosParaAbrirTurmaPort(
        String codigo,
        Curso curso,
        Sala sala,
        Turno turno,
        LocalDate dataInicio,
        LocalDate dataFim
    ) implements DadosParaAbrirTurma {

    public DadosParaAbrirTurmaPort(DadosParaAbrirTurmaDto dados, CursoJpaEntity cursoJpaEntity, SalaJpaEntity salaJpaEntity) {
        this(
                dados.codigo(),
                new Curso(new DadosParaCadastrarCursoDto(
                        cursoJpaEntity.getCodigo(),
                        cursoJpaEntity.getNome(),
                        cursoJpaEntity.getNivel(),
                        (int) cursoJpaEntity.getDuracaoEmHoras().toHours()
                )),
                new Sala(new DadosParaCadastrarSalaDto(salaJpaEntity.getNome(), salaJpaEntity.getCapacidade())),
                dados.turno(),
                dados.dataInicio(),
                dados.dataFim()
        );
    }

}
