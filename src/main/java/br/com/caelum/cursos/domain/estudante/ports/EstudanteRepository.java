package br.com.caelum.cursos.domain.estudante.ports;

import br.com.caelum.cursos.domain.estudante.Cpf;
import br.com.caelum.cursos.domain.estudante.Email;
import br.com.caelum.cursos.domain.estudante.Estudante;

public interface EstudanteRepository {

    Estudante buscarPorCpf(Cpf cpf);

    Estudante cadastrar(DadosParaCadastrarEstudante dados);

    Boolean CpfOuEmailJaCadastrado(Cpf cpf, Email email);

}
