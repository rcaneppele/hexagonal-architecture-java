package br.com.caelum.cursos.domain.estudante;

import java.util.Objects;

record Email(String email) {

    Email {
        validar(email);
    }

    private void validar(String email) {
        Objects.requireNonNull(email);
    }

}
