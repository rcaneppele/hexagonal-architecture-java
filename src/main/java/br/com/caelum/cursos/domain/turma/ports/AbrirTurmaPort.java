package br.com.caelum.cursos.domain.turma.ports;

import br.com.caelum.cursos.domain.turma.Turma;

public interface AbrirTurmaPort {

    Turma execute(DadosParaAbrirTurma dados);

}
