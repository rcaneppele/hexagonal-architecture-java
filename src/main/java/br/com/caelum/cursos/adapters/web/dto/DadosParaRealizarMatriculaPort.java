package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.EstudanteJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.ports.matricula.DadosParaRealizarMatricula;

public record DadosParaRealizarMatriculaPort(
        Cpf cpfEstudante,
        String codigoTurma
    ) implements DadosParaRealizarMatricula {

    public DadosParaRealizarMatriculaPort(DadosParaRealizarMatriculaDto dados, EstudanteJpaEntity estudanteJpaEntity, TurmaJpaEntity turmaJpaEntity) {
        this(new Cpf(estudanteJpaEntity.getCpf()), turmaJpaEntity.getCodigo());
    }

}
