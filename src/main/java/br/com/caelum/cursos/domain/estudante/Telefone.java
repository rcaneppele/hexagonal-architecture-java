package br.com.caelum.cursos.domain.estudante;

import java.util.Objects;

record Telefone(String ddd, String numero, boolean whatsapp) {

    Telefone {
        validar(ddd, numero);
    }

    private void validar(String ddd, String numero) {
        Objects.requireNonNull(ddd);
        Objects.requireNonNull(numero);
    }

}
