package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.estudante.Cpf;
import br.com.caelum.cursos.domain.estudante.Email;
import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.estudante.Telefone;
import br.com.caelum.cursos.domain.estudante.ports.DadosParaCadastrarEstudante;

import java.time.LocalDate;

public class EstudanteBuilder {

    public static Estudante build(String nome, String cpf, LocalDate dataNascimento, String email, Telefone telefone) {
        return new Estudante(new DadosParaCadastrarEstudante() {
            @Override
            public String nome() {
                return nome;
            }

            @Override
            public Cpf cpf() {
                return new Cpf(cpf);
            }

            @Override
            public LocalDate dataDeNascimento() {
                return dataNascimento;
            }

            @Override
            public Email email() {
                return new Email(email);
            }

            @Override
            public Telefone celular() {
                return telefone;
            }
        });
    }

}
