package br.com.caelum.cursos.domain.ports.curso;

import br.com.caelum.cursos.domain.core.curso.Curso;

public interface CadastrarCursoUseCase {

    Curso execute(DadosParaCadastrarCurso dados);

}
