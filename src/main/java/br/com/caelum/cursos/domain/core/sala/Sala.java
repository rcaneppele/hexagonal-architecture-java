package br.com.caelum.cursos.domain.core.sala;

import br.com.caelum.cursos.domain.ports.sala.DadosParaCadastrarSala;
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
        Objects.requireNonNull(dados, "Dados para criação da sala são obrigatórios!");
        Objects.requireNonNull(dados.nome(), "Nome da sala é obrigatório!");
        Objects.requireNonNull(dados.capacidade(), "Capacidade da sala é obrigatória!");

        this.nome = dados.nome();
        this.capacidade = dados.capacidade();
    }

}
