package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.domain.core.curso.Nivel;
import br.com.caelum.cursos.domain.ports.curso.DadosParaCadastrarCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record DadosParaCadastrarCursoDto(
        @NotBlank(message = "Código do curso é obrigatório!")
        String codigo,
        @NotBlank(message = "Nome do curso é obrigatório!")
        String nome,
        @NotNull(message = "Nível do curso é obrigatório!")
        Nivel nivel,
        @NotNull(message = "Duração do curso é obrigatória!")
        Integer duracao
        ) implements DadosParaCadastrarCurso {

    @Override
    public Duration duracaoEmHoras() {
        return Duration.ofHours(duracao);
    }

}
