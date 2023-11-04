package br.com.caelum.cursos.domain.estudante;

import java.util.Objects;

public record Email(String email) {

    public Email {
        validar(email);
    }

    private void validar(String email) {
        Objects.requireNonNull(email);
    }

}
