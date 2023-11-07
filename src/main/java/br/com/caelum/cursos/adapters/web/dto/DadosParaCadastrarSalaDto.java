package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.domain.ports.sala.DadosParaCadastrarSala;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosParaCadastrarSalaDto(
        @NotBlank(message = "Nome da sala é obrigatório!")
        String nome,
        @NotNull(message = "Capacidade da sala é obrigatória!")
        @DecimalMin(value = "8", message = "Capacidade mínima da sala deve ser 8!")
        Integer capacidade) implements DadosParaCadastrarSala {

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public Integer capacidade() {
        return capacidade;
    }

}
