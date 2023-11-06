package br.com.caelum.cursos.domain.estudante;

import br.com.caelum.cursos.domain.estudante.ports.DadosParaCadastrarEstudante;
import br.com.caelum.cursos.domain.matricula.Matricula;
import br.com.caelum.cursos.domain.turma.Turma;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "cpf")
@ToString(of = {"nome", "cpf"})
public class Estudante {

    private String nome;
    private Cpf cpf;
    private LocalDate dataDeNascimento;
    private Email email;
    private Telefone celular;

    private Set<Matricula> matriculas = new HashSet<>();

    public Estudante(String nome, Cpf cpf, LocalDate dataDeNascimento, Email email, Telefone celular) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.celular = celular;
    }

    public Estudante(DadosParaCadastrarEstudante dados) {
        this(dados.nome(), dados.cpf(), dados.dataDeNascimento(), dados.email(), dados.celular());
    }

    public boolean estaComLimiteDeTurmasEmAndamento() {
        var turmasEmAndamento = matriculas
                .stream()
                .map(Matricula::getTurma)
                .filter(Turma::isEmAndamento)
                .count();
        return turmasEmAndamento == 2;
    }

    public void registrarMatricula(Matricula matricula) {
        this.matriculas.add(matricula);
    }

    public boolean estaMatriculadoNaTurma(Turma turma) {
        return this.matriculas
                .stream()
                .map(Matricula::getTurma)
                .anyMatch(t -> t.equals(turma));
    }

}
