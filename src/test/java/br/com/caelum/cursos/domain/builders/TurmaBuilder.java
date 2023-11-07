package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.sala.Sala;
import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.core.turma.Turno;
import br.com.caelum.cursos.domain.ports.turma.DadosParaAbrirTurma;

import java.time.LocalDate;

public class TurmaBuilder {

    public static Turma build(String codigo, LocalDate dataInicio, LocalDate dataFim, Turno turno, Curso curso, Sala sala) {
        return new Turma(new DadosParaAbrirTurma() {
            @Override
            public String codigo() {
                return codigo;
            }

            @Override
            public Curso curso() {
                return curso;
            }

            @Override
            public Sala sala() {
                return sala;
            }

            @Override
            public Turno turno() {
                return turno;
            }

            @Override
            public LocalDate dataInicio() {
                return dataInicio;
            }

            @Override
            public LocalDate dataFim() {
                return dataFim;
            }
        });
    }

    public static Turma buildFrom(Turma modelo, LocalDate dataInicio, LocalDate dataFim) {
        return build(modelo.getCodigo(), dataInicio, dataFim, modelo.getTurno(), modelo.getCurso(), modelo.getSala());
    }

    public static Turma buildFrom(Turma modelo, Sala sala) {
        return build(modelo.getCodigo(), modelo.getDataInicio(), modelo.getDataFim(), modelo.getTurno(), modelo.getCurso(), sala);
    }

}
