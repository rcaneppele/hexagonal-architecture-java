package br.com.caelum.cursos.domain.turma;

import lombok.Getter;

@Getter
enum Turno {

    MATUTINO("Matutino"),
    VESPERTINO("Vespertino"),
    INTEGRAL("Integral"),
    NOTURNO("Noturno");

    private final String descricao;

    Turno(String descricao) {
        this.descricao = descricao;
    }

}
