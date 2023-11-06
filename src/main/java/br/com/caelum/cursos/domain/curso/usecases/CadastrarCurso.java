package br.com.caelum.cursos.domain.curso.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.curso.ports.CadastrarCursoPort;
import br.com.caelum.cursos.domain.curso.ports.CursoRepository;
import br.com.caelum.cursos.domain.curso.ports.DadosParaCadastrarCurso;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarCurso implements CadastrarCursoPort {

    private final CursoRepository repository;

    public void execute(DadosParaCadastrarCurso dados) {
        validarCurso(dados);
        repository.save(new Curso(dados.codigo(), dados.nome(), dados.nivel(), dados.duracaoEmHoras()));
    }

    private void validarCurso(DadosParaCadastrarCurso dados) {
        if (dados.duracaoEmHoras().toHours() < 4) {
            throw new RegraDeNegocioException("Cadastro não realizado: Duração mínima deve ser de 4 horas!");
        }

        var cursoJaCadastrado = repository.existsByCodigo(dados.codigo());
        if (cursoJaCadastrado) {
            throw new RegraDeNegocioException("Cadastro não realizado: Código já utilizado em outro curso!");
        }
    }

}
