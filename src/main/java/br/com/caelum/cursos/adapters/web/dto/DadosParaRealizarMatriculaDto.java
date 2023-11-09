package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.MatriculaJpaEntity;
import jakarta.validation.constraints.NotNull;

public record DadosParaRealizarMatriculaDto(
        @NotNull(message = "Id do estudante é obrigatório!")
        Long idEstudante,
        @NotNull(message = "Id da turma é obrigatório!")
        Long idTurma
        ) {

        public DadosParaRealizarMatriculaDto(MatriculaJpaEntity entity) {
                this(entity.getEstudante().getId(),entity.getTurma().getId());
        }

}
