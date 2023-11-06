package br.com.caelum.cursos.domain.curso.ports;

import br.com.caelum.cursos.domain.curso.Nivel;

import java.time.Duration;

public interface DadosParaCadastrarCurso {

    String codigo();
    String nome();
    Nivel nivel();
    Duration duracaoEmHoras();

}
