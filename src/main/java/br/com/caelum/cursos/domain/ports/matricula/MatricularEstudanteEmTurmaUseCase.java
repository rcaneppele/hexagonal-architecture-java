package br.com.caelum.cursos.domain.ports.matricula;

import br.com.caelum.cursos.domain.core.matricula.Matricula;

public interface MatricularEstudanteEmTurmaUseCase {

    Matricula execute(DadosParaRealizarMatricula dados);

}
