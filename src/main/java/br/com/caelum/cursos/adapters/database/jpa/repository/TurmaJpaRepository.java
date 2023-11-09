package br.com.caelum.cursos.adapters.database.jpa.repository;

import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaPort;
import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.sala.Sala;
import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.ports.turma.TurmaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface TurmaJpaRepository extends TurmaRepository, JpaRepository<TurmaJpaEntity, Long> {

    TurmaJpaEntity findByCodigo(String codigo);

    Boolean existsByCodigo(String codigo);

    Integer countByCursoCodigoAndDataFimGreaterThan(String codigo, LocalDate data);

    @Query("""
            SELECT 
                CASE WHEN COUNT(*) > 0 THEN true ELSE false END 
            FROM 
                TurmaJpaEntity t
            WHERE 
                t.sala.nome = :sala
                AND 
                    (
                        (t.dataInicio <= :dataFim AND t.dataFim >= :dataInicio)
                        OR 
                        (t.dataInicio >= :dataInicio AND t.dataFim <= :dataFim)
                    )
            """)
    Boolean salaJaOcupadaNoPeriodo(String sala, LocalDate dataInicio, LocalDate dataFim);

    @Override
    default Boolean salaJaOcupadaNoPeriodo(Sala sala, LocalDate dataInicio, LocalDate dataFim) {
        return this.salaJaOcupadaNoPeriodo(sala.getNome(), dataInicio, dataFim);
    }

    @Override
    default Boolean codigoJaCadastrado(String codigo) {
        return this.existsByCodigo(codigo);
    }

    @Override
    default Turma buscarPorCodigo(String codigo) {
        var entity = this.findByCodigo(codigo);
        var dados = new DadosParaAbrirTurmaDto(entity);
        return new Turma(new DadosParaAbrirTurmaPort(dados, entity.getCurso(), entity.getSala()));
    }

    @Override
    default Integer quantidadeDeTurmasEmAbertoDoCurso(Curso curso) {
        return this.countByCursoCodigoAndDataFimGreaterThan(curso.getCodigo(), LocalDate.now());
    }

    @Override
    default void abrir(Turma turma) {
        //nao da para salvar a turma daqui, pois precisa carregar os relacionamentos =/
    }

}
