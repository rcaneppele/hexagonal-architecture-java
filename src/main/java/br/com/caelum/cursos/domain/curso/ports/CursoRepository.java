package br.com.caelum.cursos.domain.curso.ports;

import br.com.caelum.cursos.domain.curso.Curso;

public interface CursoRepository {

    Boolean codigoJaCadastrado(String codigo);

    void cadastrar(Curso curso);

}
