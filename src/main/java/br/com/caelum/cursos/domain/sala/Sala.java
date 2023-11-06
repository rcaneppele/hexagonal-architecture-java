package br.com.caelum.cursos.domain.sala;

import br.com.caelum.cursos.domain.sala.ports.DadosParaCadastrarSala;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@EqualsAndHashCode(of = "nome")
@ToString(of = {"nome", "capacidade"})
public class Sala {

    protected String nome;
    protected Integer capacidade;

    public Sala(DadosParaCadastrarSala dados) {
        Objects.requireNonNull(dados);
        Objects.requireNonNull(dados.nome());
        Objects.requireNonNull(dados.capacidade());

        this.nome = dados.nome();
        this.capacidade = dados.capacidade();
    }

}
