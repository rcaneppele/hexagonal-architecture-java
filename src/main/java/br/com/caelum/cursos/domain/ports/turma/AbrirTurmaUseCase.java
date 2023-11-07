package br.com.caelum.cursos.domain.ports.turma;

import br.com.caelum.cursos.domain.core.turma.Turma;

public interface AbrirTurmaUseCase {

    Turma execute(DadosParaAbrirTurma dados);

}
