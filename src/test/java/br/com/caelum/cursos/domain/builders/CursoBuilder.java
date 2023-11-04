package br.com.caelum.cursos.domain.builders;

import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.curso.Nivel;

import java.time.Duration;

public class CursoBuilder {

    public static Curso build(String codigo, String nome, int cargaHoraria) {
        return new Curso(
                codigo,
                nome,
                Nivel.BASICO,
                Duration.ofHours(cargaHoraria)
        );
    }

}
