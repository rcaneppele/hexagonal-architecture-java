package br.com.caelum.cursos.domain.curso.ports;

import br.com.caelum.cursos.domain.curso.Curso;

public interface CursoRepository {

    Boolean existsByCodigo(String codigo);

    void save(Curso curso);

}
