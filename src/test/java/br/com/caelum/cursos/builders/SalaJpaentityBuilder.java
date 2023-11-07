package br.com.caelum.cursos.builders;

import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarSalaDto;

public class SalaJpaentityBuilder {

    public static SalaJpaEntity build(Long id, String nome, Integer capacidade) {
        return new SalaJpaEntity(id, nome, capacidade);
    }

    public static SalaJpaEntity build() {
        return build(50l, "Sala 01", 20);
    }

    public static SalaJpaEntity build(DadosParaCadastrarSalaDto dadosCadastro) {
        return build(50l, dadosCadastro.nome(), dadosCadastro.capacidade());
    }

}
