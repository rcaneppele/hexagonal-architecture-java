package br.com.caelum.cursos.domain.curso;

import lombok.Getter;

@Getter
enum Nivel {

    BASICO("Básico"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado");

    private final String descricao;

    Nivel(String descricao) {
        this.descricao = descricao;
    }

}
