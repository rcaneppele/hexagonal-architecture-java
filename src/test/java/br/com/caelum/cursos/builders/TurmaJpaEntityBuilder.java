package br.com.caelum.cursos.builders;

import br.com.caelum.cursos.adapters.database.jpa.entity.CursoJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaDto;
import br.com.caelum.cursos.domain.core.curso.Nivel;
import br.com.caelum.cursos.domain.core.turma.Turno;

import java.time.LocalDate;

public class TurmaJpaEntityBuilder {

    public static TurmaJpaEntity build(Long id, String codigo, CursoJpaEntity curso, SalaJpaEntity sala, Turno turno, LocalDate dataInicio, LocalDate dataFim) {
        return new TurmaJpaEntity(id, codigo, curso, sala, turno, dataInicio, dataFim);
    }

    public static TurmaJpaEntity build() {
        return build(
                50l,
                "T-001",
                CursoJpaEntityBuilder.build(),
                SalaJpaentityBuilder.build(),
                Turno.INTEGRAL,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(6));
    }

    public static TurmaJpaEntity build(long id, DadosParaAbrirTurmaDto dadosCadastro) {
        return build(
                id,
                dadosCadastro.codigo(),
                CursoJpaEntityBuilder.build(dadosCadastro.idCurso(),"C-001", "Curso", Nivel.BASICO, 10),
                SalaJpaentityBuilder.build(dadosCadastro.idSala(), "Sala 01", 10),
                dadosCadastro.turno(),
                dadosCadastro.dataInicio(),
                dadosCadastro.dataFim());
    }

}
