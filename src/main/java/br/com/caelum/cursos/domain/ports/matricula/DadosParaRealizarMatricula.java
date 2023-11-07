package br.com.caelum.cursos.domain.ports.matricula;

import br.com.caelum.cursos.domain.core.estudante.Cpf;

public interface DadosParaRealizarMatricula {

    Cpf cpfEstudante();
    String codigoTurma();

}
