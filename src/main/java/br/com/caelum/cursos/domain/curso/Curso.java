package br.com.caelum.cursos.domain.curso;

import br.com.caelum.cursos.domain.curso.ports.DadosParaCadastrarCurso;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.util.Objects;

@Getter
@EqualsAndHashCode(of = "codigo")
@ToString(of = {"codigo", "nome"})
public class Curso {

    private String codigo;
    private String nome;
    private Nivel nivel;
    private Duration duracaoEmHoras;

    public Curso(DadosParaCadastrarCurso dados) {
        Objects.requireNonNull(dados, "Dados para criação do curso são obrigatórios!");
        Objects.requireNonNull(dados.codigo(), "Código do curso é obrigatório!");
        Objects.requireNonNull(dados.nome(), "Nome do curso é obrigatório!");
        Objects.requireNonNull(dados.nivel(), "Nível do curso é obrigatório!");
        Objects.requireNonNull(dados.duracaoEmHoras(), "Duração do curso é obrigatória!");

        this.codigo = dados.codigo();
        this.nome = dados.nome();
        this.nivel = dados.nivel();
        this.duracaoEmHoras = dados.duracaoEmHoras();
    }

}
