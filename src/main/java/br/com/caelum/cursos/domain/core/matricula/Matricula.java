package br.com.caelum.cursos.domain.core.matricula;

import br.com.caelum.cursos.domain.core.estudante.Estudante;
import br.com.caelum.cursos.domain.core.turma.Turma;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"turma", "estudante"})
@ToString(of = {"codigo", "turma", "estudante"})
public class Matricula {

    private String codigo;
    private Turma turma;
    private Estudante estudante;

    public Matricula(Turma turma, Estudante estudante) {
        this.codigo = UUID.randomUUID().toString();
        this.turma = turma;
        this.estudante = estudante;
        this.turma.registrarMatricula(this);
        this.estudante.registrarMatricula(this);
    }

}
