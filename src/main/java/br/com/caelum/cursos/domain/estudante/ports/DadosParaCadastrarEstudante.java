package br.com.caelum.cursos.domain.estudante.ports;

import br.com.caelum.cursos.domain.estudante.Cpf;
import br.com.caelum.cursos.domain.estudante.Email;
import br.com.caelum.cursos.domain.estudante.Telefone;

import java.time.LocalDate;

public interface DadosParaCadastrarEstudante {

    String nome();
    Cpf cpf();
    LocalDate dataDeNascimento();
    Email email();
    Telefone celular();

}
