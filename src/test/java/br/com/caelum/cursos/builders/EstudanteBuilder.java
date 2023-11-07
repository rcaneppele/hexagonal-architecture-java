package br.com.caelum.cursos.builders;

import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.core.estudante.Email;
import br.com.caelum.cursos.domain.core.estudante.Estudante;
import br.com.caelum.cursos.domain.core.estudante.Telefone;
import br.com.caelum.cursos.domain.ports.estudante.DadosParaCadastrarEstudante;

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
