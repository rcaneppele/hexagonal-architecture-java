package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;

public record DadosSalaDto(Long id, String nome, Integer capacidade) {

    public DadosSalaDto(SalaJpaEntity salaJpaEntity) {
        this(salaJpaEntity.getId(), salaJpaEntity.getNome(), salaJpaEntity.getCapacidade());
    }

}
