package br.com.caelum.cursos.domain.core.matricula;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.core.estudante.Estudante;
import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.ports.estudante.EstudanteRepository;
import br.com.caelum.cursos.domain.ports.matricula.DadosParaRealizarMatricula;
import br.com.caelum.cursos.domain.ports.matricula.MatriculaRepository;
import br.com.caelum.cursos.domain.ports.matricula.MatricularEstudanteEmTurmaUseCase;
import br.com.caelum.cursos.domain.ports.turma.TurmaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatricularEstudanteEmTurmaService implements MatricularEstudanteEmTurmaUseCase {

    private final TurmaRepository turmaRepository;
    private final EstudanteRepository estudanteRepository;
    private final MatriculaRepository matriculaRepository;

    public Matricula execute(DadosParaRealizarMatricula dados) {
        var turma = turmaRepository.buscarPorCodigo(dados.codigoTurma());
        validarTurma(turma);

        var estudante = estudanteRepository.buscarPorCpf(dados.cpfEstudante());
        validarEstudante(estudante, turma);

        var matricula = new Matricula(turma, estudante);
        matriculaRepository.registrar(matricula);
        return matricula;
    }

    private void validarTurma(Turma turma) {
        if (turma.estaLotada()) {
            throw new RegraDeNegocioException("Matricula não realizada: turma está lotada!");
        }

        if (!turma.estaComMatriculasAbertas()) {
            throw new RegraDeNegocioException("Matricula não realizada: turma com período de matriculas encerado!");
        }
    }

    private void validarEstudante(Estudante estudante, Turma turma) {
        if (estudante.estaMatriculadoNaTurma(turma)) {
            throw new RegraDeNegocioException("Matricula não realizada: estudante já possui matricula para esta turma!");
        }

        if (estudante.estaComLimiteDeTurmasEmAndamento()) {
            throw new RegraDeNegocioException("Matricula não realizada: estudante com limite de turmas em andamento!");
        }
    }

}
