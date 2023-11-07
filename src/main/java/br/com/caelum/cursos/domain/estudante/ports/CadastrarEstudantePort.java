package br.com.caelum.cursos.domain.estudante.ports;

import br.com.caelum.cursos.domain.estudante.Estudante;

public interface CadastrarEstudantePort {

    Estudante execute(DadosParaCadastrarEstudante dados);

}
