package br.com.caelum.cursos.domain.estudante;

import java.util.Objects;

record Cpf(String numero) {

    Cpf {
        validar(numero);
    }

    private void validar(String numero) {
        Objects.requireNonNull(numero);
    }

}
