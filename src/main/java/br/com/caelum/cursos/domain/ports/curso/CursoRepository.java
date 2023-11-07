package br.com.caelum.cursos.domain.ports.curso;

import br.com.caelum.cursos.domain.core.curso.Curso;

public interface CursoRepository {

    Boolean codigoJaCadastrado(String codigo);

    void cadastrar(Curso curso);

}
