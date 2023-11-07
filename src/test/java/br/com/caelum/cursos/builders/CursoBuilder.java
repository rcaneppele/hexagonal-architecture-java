package br.com.caelum.cursos.builders;

import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.core.curso.Nivel;
import br.com.caelum.cursos.domain.ports.curso.DadosParaCadastrarCurso;

import java.time.Duration;

public class CursoBuilder {

    public static Curso build(String codigo, String nome, int cargaHoraria) {
        return new Curso(new DadosParaCadastrarCurso() {
            @Override
            public String codigo() {
                return codigo;
            }

            @Override
            public String nome() {
                return nome;
            }

            @Override
            public Nivel nivel() {
                return Nivel.BASICO;
            }

            @Override
            public Duration duracaoEmHoras() {
                return Duration.ofHours(cargaHoraria);
            }
        });
    }

}
