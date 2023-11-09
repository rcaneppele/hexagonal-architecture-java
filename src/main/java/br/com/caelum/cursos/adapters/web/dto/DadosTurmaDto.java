package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.domain.core.turma.Turno;

import java.time.LocalDate;

public record DadosTurmaDto(Long id, String codigo, Long idCurso, Long idSala, Turno turno, LocalDate dataInicio, LocalDate dataFim) {

    public DadosTurmaDto(TurmaJpaEntity entity) {
        this(
                entity.getId(),
                entity.getCodigo(),
                entity.getCurso().getId(),
                entity.getSala().getId(),
                entity.getTurno(),
                entity.getDataInicio(),
                entity.getDataFim()
        );
    }

}
