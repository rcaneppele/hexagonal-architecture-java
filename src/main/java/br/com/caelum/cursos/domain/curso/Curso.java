package br.com.caelum.cursos.domain.curso;

import br.com.caelum.cursos.domain.curso.ports.DadosParaCadastrarCurso;
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

    public Curso(String codigo, String nome, Nivel nivel, Duration duracaoEmHoras) {
        this.codigo = codigo;
        this.nome = nome;
        this.nivel = nivel;
        this.duracaoEmHoras = duracaoEmHoras;
    }

    public Curso(DadosParaCadastrarCurso dados) {
        this(dados.codigo(), dados.nome(), dados.nivel(), dados.duracaoEmHoras());
    }

}
