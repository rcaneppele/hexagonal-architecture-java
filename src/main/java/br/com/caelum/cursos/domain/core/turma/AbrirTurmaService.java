package br.com.caelum.cursos.domain.core.turma;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.ports.turma.AbrirTurmaUseCase;
import br.com.caelum.cursos.domain.ports.turma.DadosParaAbrirTurma;
import br.com.caelum.cursos.domain.ports.turma.TurmaRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class AbrirTurmaService implements AbrirTurmaUseCase {

    private final TurmaRepository repository;

    public Turma execute(DadosParaAbrirTurma dados) {
        validarTurma(dados);
        var turma = new Turma(dados);
        repository.abrir(turma);
        return turma;
    }

    private void validarTurma(DadosParaAbrirTurma dados) {
        validarDatas(dados);
        validarCodigo(dados);
        validarSala(dados);
        validarLimiteDeTurmas(dados);
    }

    private void validarDatas(DadosParaAbrirTurma dados) {
        if (dados.dataInicio().isBefore(LocalDate.now())) {
            throw new RegraDeNegocioException("Cadastro não realizado: Data de início não pode ser anterior a hoje!");
        }

        if (dados.dataFim().isBefore(dados.dataInicio())) {
            throw new RegraDeNegocioException("Cadastro não realizado: Data fim deve ser posterior a data de início!");
        }
    }

    private void validarCodigo(DadosParaAbrirTurma dados) {
        var turmaJaCadastrada = repository.codigoJaCadastrado(dados.codigo());
        if (turmaJaCadastrada) {
            throw new RegraDeNegocioException("Cadastro não realizado: Código já utilizado em outra turma!");
        }
    }

    private void validarSala(DadosParaAbrirTurma dados) {
        var salaOcupada = repository.salaJaOcupadaNoPeriodo(dados.sala(), dados.dataInicio(), dados.dataFim());
        if (salaOcupada) {
            throw new RegraDeNegocioException("Cadastro não realizado: Sala já utilizada por outra turma no mesmo período!");
        }
    }

    private void validarLimiteDeTurmas(DadosParaAbrirTurma dados) {
        var turmasEmAndamento = repository.quantidadeDeTurmasEmAbertoDoCurso(dados.curso());
        if (turmasEmAndamento == 4) {
            throw new RegraDeNegocioException("Cadastro não realizado: Curso atingiu limite de 4 turmas em andamento!");
        }
    }

}
