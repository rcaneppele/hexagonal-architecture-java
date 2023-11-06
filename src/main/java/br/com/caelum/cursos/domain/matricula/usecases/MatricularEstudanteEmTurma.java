package br.com.caelum.cursos.domain.matricula.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.estudante.ports.EstudanteRepository;
import br.com.caelum.cursos.domain.matricula.Matricula;
import br.com.caelum.cursos.domain.matricula.ports.DadosParaRealizarMatricula;
import br.com.caelum.cursos.domain.matricula.ports.MatriculaRepository;
import br.com.caelum.cursos.domain.matricula.ports.MatricularEstudanteEmTurmaPort;
import br.com.caelum.cursos.domain.turma.Turma;
import br.com.caelum.cursos.domain.turma.ports.TurmaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatricularEstudanteEmTurma implements MatricularEstudanteEmTurmaPort {

    private final TurmaRepository turmaRepository;
    private final EstudanteRepository estudanteRepository;
    private final MatriculaRepository matriculaRepository;

    public void execute(DadosParaRealizarMatricula dados) {
        var turma = turmaRepository.buscarPorCodigo(dados.codigoTurma());
        validarTurma(turma);

        var estudante = estudanteRepository.buscarPorCpf(dados.cpfEstudante());
        validarEstudante(estudante, turma);

        var matricula = new Matricula(turma, estudante);
        matriculaRepository.registrar(matricula);
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
