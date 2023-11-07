package br.com.caelum.cursos.domain.ports.estudante;

import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.core.estudante.Email;
import br.com.caelum.cursos.domain.core.estudante.Telefone;

import java.time.LocalDate;

public interface DadosParaCadastrarEstudante {

    String nome();
    Cpf cpf();
    LocalDate dataDeNascimento();
    Email email();
    Telefone celular();

}
