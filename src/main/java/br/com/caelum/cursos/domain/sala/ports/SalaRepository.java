package br.com.caelum.cursos.domain.sala.ports;

import br.com.caelum.cursos.domain.sala.Sala;

public interface SalaRepository {

    Boolean nomeJaCadastrado(String nome);

    Sala cadastrar(DadosParaCadastrarSala dados);

}
