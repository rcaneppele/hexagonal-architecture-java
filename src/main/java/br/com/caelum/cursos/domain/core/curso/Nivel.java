package br.com.caelum.cursos.domain.core.curso;

import lombok.Getter;

@Getter
public
enum Nivel {

    BASICO("Básico"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado");

    private final String descricao;

    Nivel(String descricao) {
        this.descricao = descricao;
    }

}
