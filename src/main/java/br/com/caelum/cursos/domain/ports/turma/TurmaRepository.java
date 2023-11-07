package br.com.caelum.cursos.domain.ports.turma;

import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.sala.Sala;
import br.com.caelum.cursos.domain.core.turma.Turma;

import java.time.LocalDate;

public interface TurmaRepository {

    Turma buscarPorCodigo(String codigo);

    void abrir(Turma turma);

    Boolean codigoJaCadastrado(String codigo);

    Boolean salaJaOcupadaNoPeriodo(Sala sala, LocalDate dataInicio, LocalDate dataFim);

    Integer quantidadeDeTurmasEmAbertoDoCurso(Curso curso);

}
