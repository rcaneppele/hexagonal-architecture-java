package br.com.caelum.cursos.domain.turma.ports;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.turma.Turma;

import java.time.LocalDate;

public interface TurmaRepository {

    Turma buscarPorCodigo(String codigo);

    void abrir(Turma turma);

    Boolean codigoJaCadastrado(String codigo);

    Boolean salaJaOcupadaNoPeriodo(Sala sala, LocalDate dataInicio, LocalDate dataFim);

    Integer quantidadeDeTurmasEmAbertoDoCurso(Curso curso);

}
