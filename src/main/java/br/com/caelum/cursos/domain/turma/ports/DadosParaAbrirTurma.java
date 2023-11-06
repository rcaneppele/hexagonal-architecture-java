package br.com.caelum.cursos.domain.turma.ports;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.turma.Turno;

import java.time.LocalDate;

public interface DadosParaAbrirTurma {

    String codigo();
    Curso curso();
    Sala sala();
    Turno turno();
    LocalDate dataInicio();
    LocalDate dataFim();

}
