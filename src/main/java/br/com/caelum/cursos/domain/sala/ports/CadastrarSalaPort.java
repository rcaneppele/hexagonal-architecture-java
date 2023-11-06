package br.com.caelum.cursos.domain.sala.ports;

import br.com.caelum.cursos.domain.sala.Sala;

public interface CadastrarSalaPort {

    Sala execute(DadosParaCadastrarSala dados);

}
