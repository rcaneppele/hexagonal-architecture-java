package br.com.caelum.cursos.domain.core.curso;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.ports.curso.CadastrarCursoUseCase;
import br.com.caelum.cursos.domain.ports.curso.CursoRepository;
import br.com.caelum.cursos.domain.ports.curso.DadosParaCadastrarCurso;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarCursoService implements CadastrarCursoUseCase {

    private final CursoRepository repository;

    public Curso execute(DadosParaCadastrarCurso dados) {
        validarCurso(dados);
        var curso = new Curso(dados);
        repository.cadastrar(curso);
        return curso;
    }

    private void validarCurso(DadosParaCadastrarCurso dados) {
        if (dados.duracaoEmHoras().toHours() < 4) {
            throw new RegraDeNegocioException("Cadastro não realizado: Duração mínima deve ser de 4 horas!");
        }

        var cursoJaCadastrado = repository.codigoJaCadastrado(dados.codigo());
        if (cursoJaCadastrado) {
            throw new RegraDeNegocioException("Cadastro não realizado: Código já utilizado em outro curso!");
        }
    }

}
