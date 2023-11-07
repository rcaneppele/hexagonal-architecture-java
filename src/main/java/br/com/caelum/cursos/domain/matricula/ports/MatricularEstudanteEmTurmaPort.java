package br.com.caelum.cursos.domain.matricula.ports;

import br.com.caelum.cursos.domain.matricula.Matricula;

public interface MatricularEstudanteEmTurmaPort {

    Matricula execute(DadosParaRealizarMatricula dados);

}
