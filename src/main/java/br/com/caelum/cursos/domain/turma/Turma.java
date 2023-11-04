package br.com.caelum.cursos.domain.turma;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.matricula.Matricula;
import br.com.caelum.cursos.domain.sala.Sala;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Matricula> matriculas = new HashSet<>();

    public boolean estaLotada() {
        return this.matriculas.size() == this.sala.getCapacidade();
    }

    public boolean estaComMatriculasAbertas() {
        var dataLimiteParaMatriculas = this.dataInicio.plusDays(1);
        var hoje = LocalDate.now();
        return !hoje.isAfter(dataLimiteParaMatriculas);
    }

    public  boolean isEmAndamento() {
        var hoje = LocalDate.now();
        return !hoje.isAfter(this.dataFim);
    }

    public void registrarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

}
