package br.com.caelum.cursos.domain.sala.ports;

import br.com.caelum.cursos.domain.sala.Sala;

public interface SalaRepository {

    Boolean existsByNome(String nome);

    void save(Sala sala);

}
