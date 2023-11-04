package br.com.caelum.cursos.domain.turma.ports;

import br.com.caelum.cursos.domain.turma.Turma;

public interface TurmaRepository {

    Turma findByCodigo(String codigo);

}
