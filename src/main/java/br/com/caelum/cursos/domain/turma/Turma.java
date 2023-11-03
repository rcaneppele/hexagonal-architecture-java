package br.com.caelum.cursos.domain.turma;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.sala.Sala;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(of = "codigo")
@ToString(of = {"codigo", "curso", "turno", "dataInicio", "dataFim"})
public class Turma {

    private String codigo;
    private Curso curso;
    private Sala sala;
    private Turno turno;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
