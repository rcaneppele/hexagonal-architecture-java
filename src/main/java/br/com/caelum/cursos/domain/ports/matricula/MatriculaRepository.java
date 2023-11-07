package br.com.caelum.cursos.domain.ports.matricula;

import br.com.caelum.cursos.domain.core.matricula.Matricula;

public interface MatriculaRepository {

    void registrar(Matricula matricula);

}
