package br.com.caelum.cursos.domain.ports.curso;

import br.com.caelum.cursos.domain.core.curso.Nivel;

import java.time.Duration;

public interface DadosParaCadastrarCurso {

    String codigo();
    String nome();
    Nivel nivel();
    Duration duracaoEmHoras();

}
