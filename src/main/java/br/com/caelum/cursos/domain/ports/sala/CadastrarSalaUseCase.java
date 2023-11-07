package br.com.caelum.cursos.domain.ports.sala;

import br.com.caelum.cursos.domain.core.sala.Sala;

public interface CadastrarSalaUseCase {

    Sala execute(DadosParaCadastrarSala dados);

}
