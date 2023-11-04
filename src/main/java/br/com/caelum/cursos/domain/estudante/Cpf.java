package br.com.caelum.cursos.domain.estudante;

import java.util.Objects;

public record Cpf(String numero) {

    public Cpf {
        validar(numero);
    }

    private void validar(String numero) {
        Objects.requireNonNull(numero);
    }

}
