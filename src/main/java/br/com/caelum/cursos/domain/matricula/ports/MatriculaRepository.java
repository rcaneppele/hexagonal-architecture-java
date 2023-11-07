package br.com.caelum.cursos.domain.matricula.ports;

import br.com.caelum.cursos.domain.matricula.Matricula;

public interface MatriculaRepository {

    Matricula registrar(DadosParaRealizarMatricula dados);

}
