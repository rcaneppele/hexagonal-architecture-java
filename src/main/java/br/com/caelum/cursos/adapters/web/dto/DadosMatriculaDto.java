package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.MatriculaJpaEntity;

public record DadosMatriculaDto(Long id, String codigo, Long idEstudante, Long idTurma) {

    public DadosMatriculaDto(MatriculaJpaEntity entity) {
        this(
                entity.getId(),
                entity.getCodigo(),
                entity.getEstudante().getId(),
                entity.getTurma().getId()
        );
    }

}
