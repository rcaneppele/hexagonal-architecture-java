package br.com.caelum.cursos.domain.matricula.ports;

import br.com.caelum.cursos.domain.estudante.Cpf;

public interface DadosParaRealizarMatricula {

    Cpf cpfEstudante();
    String codigoTurma();

}
