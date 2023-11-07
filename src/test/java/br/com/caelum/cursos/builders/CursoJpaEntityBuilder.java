package br.com.caelum.cursos.builders;

import br.com.caelum.cursos.adapters.database.jpa.entity.CursoJpaEntity;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarCursoDto;
import br.com.caelum.cursos.domain.core.curso.Nivel;

import java.time.Duration;

public class CursoJpaEntityBuilder {

    public static CursoJpaEntity build(Long id, String codigo, String nome, Nivel nivel, int cargaHoraria) {
        return new CursoJpaEntity(id, codigo, nome, nivel, Duration.ofHours(cargaHoraria));
    }

    public static CursoJpaEntity build() {
        return build(50l, "xpto", "curso xpto", Nivel.BASICO, 20);
    }

    public static CursoJpaEntity build(long id, DadosParaCadastrarCursoDto dadosCadastro) {
        return build(id, dadosCadastro.codigo(), dadosCadastro.nome(), dadosCadastro.nivel(), dadosCadastro.duracao());
    }

}
