package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.CursoJpaEntity;
import br.com.caelum.cursos.domain.core.curso.Nivel;

public record DadosCursoDto(Long id, String codigo, String nome, Nivel nivel, Long duracaoEmHoras) {

    public DadosCursoDto(CursoJpaEntity cursoJpaEntity) {
        this(cursoJpaEntity.getId(), cursoJpaEntity.getNome(), cursoJpaEntity.getNome(), cursoJpaEntity.getNivel(), cursoJpaEntity.getDuracaoEmHoras().toHours());
    }

}
