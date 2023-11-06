package br.com.caelum.cursos.domain.estudante.ports;

import br.com.caelum.cursos.domain.estudante.Cpf;
import br.com.caelum.cursos.domain.estudante.Email;
import br.com.caelum.cursos.domain.estudante.Estudante;

public interface EstudanteRepository {

    Estudante findByCpf(Cpf cpf);

    void save(Estudante estudante);

    Boolean existsByCpfOrEmail(Cpf cpf, Email email);

}
