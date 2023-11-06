package br.com.caelum.cursos.domain.turma.ports;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.turma.Turma;

import java.time.LocalDate;

public interface TurmaRepository {

    Turma findByCodigo(String codigo);

    void save(Turma turma);

    Boolean existsByCodigo(String codigo);

    Boolean existsBySalaAndDatas(Sala sala, LocalDate dataInicio, LocalDate dataFim);

    Integer countEmAndamentoByCurso(Curso curso);

}
