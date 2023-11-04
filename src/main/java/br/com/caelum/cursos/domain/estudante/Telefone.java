package br.com.caelum.cursos.domain.estudante;

import java.util.Objects;

public record Telefone(String ddd, String numero, boolean whatsapp) {

   public Telefone {
        validar(ddd, numero);
    }

    private void validar(String ddd, String numero) {
        Objects.requireNonNull(ddd);
        Objects.requireNonNull(numero);
    }

}
