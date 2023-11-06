package br.com.caelum.cursos.domain.sala;

import br.com.caelum.cursos.domain.sala.ports.DadosParaCadastrarSala;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "nome")
@ToString(of = {"nome", "capacidade"})
public class Sala {

    private String nome;
    private Integer capacidade;

    public Sala(String nome, Integer capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public Sala(DadosParaCadastrarSala dados) {
        this(dados.nome(), dados.capacidade());
    }

}
