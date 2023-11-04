package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.estudante.Cpf;
import br.com.caelum.cursos.domain.estudante.Email;
import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.estudante.Telefone;

import java.time.LocalDate;

public class EstudanteBuilder {

    public static Estudante build(String nome, String cpf, LocalDate dataNascimento, String email, Telefone telefone) {
        return new Estudante(
                nome,
                new Cpf(cpf),
                dataNascimento,
                new Email(email),
                telefone
        );
    }

}
