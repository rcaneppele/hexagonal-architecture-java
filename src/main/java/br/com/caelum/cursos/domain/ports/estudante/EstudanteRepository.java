package br.com.caelum.cursos.domain.ports.estudante;

import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.core.estudante.Email;
import br.com.caelum.cursos.domain.core.estudante.Estudante;

public interface EstudanteRepository {

    Estudante buscarPorCpf(Cpf cpf);

    void cadastrar(Estudante estudante);

    Boolean CpfOuEmailJaCadastrado(Cpf cpf, Email email);

}
