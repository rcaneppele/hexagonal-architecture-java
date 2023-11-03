package br.com.caelum.cursos.domain.estudante;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(of = "cpf")
@ToString(of = {"nome", "cpf"})
public class Estudante {

    private String nome;
    private Cpf cpf;
    private LocalDate dataDeNascimento;
    private Email email;
    private Telefone celular;

}
