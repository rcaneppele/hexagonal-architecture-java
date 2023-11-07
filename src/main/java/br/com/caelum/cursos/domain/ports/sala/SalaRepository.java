package br.com.caelum.cursos.domain.ports.sala;

import br.com.caelum.cursos.domain.core.sala.Sala;

public interface SalaRepository {

    Boolean nomeJaCadastrado(String nome);

    void cadastrar(Sala sala);

}
