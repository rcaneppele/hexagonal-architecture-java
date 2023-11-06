package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.sala.ports.DadosParaCadastrarSala;

public class SalaBuilder {

    public static Sala build(String nome, int capacidade) {
        return new Sala(new DadosParaCadastrarSala() {
            @Override
            public String nome() {
                return nome;
            }

            @Override
            public Integer capacidade() {
                return capacidade;
            }
        });
    }

}
