package br.com.caelum.cursos.domain.core.estudante;

import br.com.caelum.cursos.domain.core.matricula.Matricula;
import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.ports.estudante.DadosParaCadastrarEstudante;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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

    public Estudante(DadosParaCadastrarEstudante dados) {
        Objects.requireNonNull(dados, "Dados para criação do estudante são obrigatórios!");
        Objects.requireNonNull(dados.nome(), "Nome do estudante é obrigatório!");
        Objects.requireNonNull(dados.cpf(), "CPF do estudante é obrigatório!");
        Objects.requireNonNull(dados.dataDeNascimento(), "Data de nascimento do estudante é obrigatória!");
        Objects.requireNonNull(dados.email(), "Email do estudante é obrigatório!");
        Objects.requireNonNull(dados.celular(), "Celular do estudante é obrigatório!");

        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataDeNascimento = dados.dataDeNascimento();
        this.email = dados.email();
        this.celular = dados.celular();
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
