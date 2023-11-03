package br.com.caelum.cursos.domain.curso;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;

@Getter
@EqualsAndHashCode(of = "codigo")
@ToString(of = {"codigo", "nome"})
public class Curso {

    private String codigo;
    private String nome;
    private Nivel nivel;
    private Duration duracaoEmHoras;

}
