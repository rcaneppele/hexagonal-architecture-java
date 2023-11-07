package br.com.caelum.cursos.domain.curso.ports;

import br.com.caelum.cursos.domain.curso.Curso;

public interface CadastrarCursoPort {

    Curso execute(DadosParaCadastrarCurso dados);

}
