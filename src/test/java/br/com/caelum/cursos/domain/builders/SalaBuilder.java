package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.sala.Sala;

public class SalaBuilder {

    public static Sala build(String nome, int capacidade) {
        return new Sala(
                nome,
                capacidade
        );
    }

}
