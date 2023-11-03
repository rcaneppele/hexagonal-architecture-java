package br.com.caelum.cursos.domain.matricula;

import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.turma.Turma;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "codigo")
@ToString(of = {"codigo", "turma", "estudante"})
public class Matricula {

    private String codigo;
    private Turma turma;
    private Estudante estudante;

}
