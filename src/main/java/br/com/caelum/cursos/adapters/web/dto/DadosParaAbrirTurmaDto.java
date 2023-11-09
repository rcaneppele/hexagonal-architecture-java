package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.domain.core.turma.Turno;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosParaAbrirTurmaDto(
        @NotBlank(message = "Codigo da turma é obrigatório!")
        String codigo,
        @NotNull(message = "Id do curso da turma é obrigatório!")
        Long idCurso,
        @NotNull(message = "Id da sala da turma é obrigatório!")
        Long idSala,
        @NotNull(message = "Turno da turma é obrigatório!")
        Turno turno,
        @NotNull(message = "Data início da turma é obrigatória!")
        @Future(message = "Data início da turma deve ser uma data futura!")
        LocalDate dataInicio,
        @NotNull(message = "Data fim da turma é obrigatória!")
        @Future(message = "Data fim da turma deve ser uma data futura!")
        LocalDate dataFim
        ) {

        public DadosParaAbrirTurmaDto(TurmaJpaEntity entity) {
                this(
                        entity.getCodigo(),
                        entity.getCurso().getId(),
                        entity.getSala().getId(),
                        entity.getTurno(),
                        entity.getDataInicio(),
                        entity.getDataFim()
                );
        }

}
