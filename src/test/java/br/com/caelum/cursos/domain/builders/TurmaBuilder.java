package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.turma.Turma;
import br.com.caelum.cursos.domain.turma.Turno;

import java.time.LocalDate;

public class TurmaBuilder {

    public static Turma build(String codigo, LocalDate dataInicio, LocalDate dataFim, Turno turno, Curso curso, Sala sala) {
        return new Turma(
                codigo,
                curso,
                sala,
                turno,
                dataInicio,
                dataFim
        );
    }

    public static Turma buildFrom(Turma modelo, LocalDate dataInicio, LocalDate dataFim) {
        return build(modelo.getCodigo(), dataInicio, dataFim, modelo.getTurno(), modelo.getCurso(), modelo.getSala());
    }

    public static Turma buildFrom(Turma modelo, Sala sala) {
        return build(modelo.getCodigo(), modelo.getDataInicio(), modelo.getDataFim(), modelo.getTurno(), modelo.getCurso(), sala);
    }

}
