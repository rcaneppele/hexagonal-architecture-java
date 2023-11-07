package br.com.caelum.cursos.domain.ports.turma;

import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.sala.Sala;
import br.com.caelum.cursos.domain.core.turma.Turno;

import java.time.LocalDate;

public interface DadosParaAbrirTurma {

    String codigo();
    Curso curso();
    Sala sala();
    Turno turno();
    LocalDate dataInicio();
    LocalDate dataFim();

}
