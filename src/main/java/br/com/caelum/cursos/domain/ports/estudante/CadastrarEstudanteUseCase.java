package br.com.caelum.cursos.domain.ports.estudante;

import br.com.caelum.cursos.domain.core.estudante.Estudante;

public interface CadastrarEstudanteUseCase {

    Estudante execute(DadosParaCadastrarEstudante dados);

}
