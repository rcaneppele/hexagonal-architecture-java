package br.com.caelum.cursos.domain.turma;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.matricula.Matricula;
import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.turma.ports.DadosParaAbrirTurma;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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

    public Turma(DadosParaAbrirTurma dados) {
        Objects.requireNonNull(dados, "Dados para criação da turma são obrigatórios!");
        Objects.requireNonNull(dados.codigo(), "Código da turma é obrigatório!");
        Objects.requireNonNull(dados.curso(), "Curso da turma é obrigatório!");
        Objects.requireNonNull(dados.sala(), "Sala da turma é obrigatória!");
        Objects.requireNonNull(dados.turno(), "Turno da turma é obrigatório!");
        Objects.requireNonNull(dados.dataInicio(), "Data de início da turma é obrigatória!");
        Objects.requireNonNull(dados.dataFim(), "Data fim da turma é obrigatória!");

        this.codigo = dados.codigo();
        this.curso = dados.curso();
        this.sala = dados.sala();
        this.turno = dados.turno();
        this.dataInicio = dados.dataInicio();
        this.dataFim = dados.dataFim();
    }

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
