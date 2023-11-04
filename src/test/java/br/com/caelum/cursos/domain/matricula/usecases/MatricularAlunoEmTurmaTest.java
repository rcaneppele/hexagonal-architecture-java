package br.com.caelum.cursos.domain.matricula.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.builders.CursoBuilder;
import br.com.caelum.cursos.domain.builders.EstudanteBuilder;
import br.com.caelum.cursos.domain.builders.SalaBuilder;
import br.com.caelum.cursos.domain.builders.TurmaBuilder;
import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.estudante.Telefone;
import br.com.caelum.cursos.domain.estudante.ports.EstudanteRepository;
import br.com.caelum.cursos.domain.matricula.Matricula;
import br.com.caelum.cursos.domain.matricula.ports.DadosParaRealizarMatricula;
import br.com.caelum.cursos.domain.matricula.ports.MatriculaRepository;
import br.com.caelum.cursos.domain.turma.Turma;
import br.com.caelum.cursos.domain.turma.Turno;
import br.com.caelum.cursos.domain.turma.ports.TurmaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MatricularAlunoEmTurmaTest {

    @InjectMocks
    private MatricularAlunoEmTurma useCase;
    @Mock
    private TurmaRepository turmaRepository;
    @Mock
    private EstudanteRepository estudanteRepository;
    @Mock
    private MatriculaRepository matriculaRepository;
    @Mock
    private DadosParaRealizarMatricula dados;
    private Estudante estudante;
    private Turma turma;

    @BeforeEach
    void setup() {
        this.estudante = EstudanteBuilder.build(
                "Estudante",
                "000.000.000-00",
                LocalDate.of(2000, 1, 1),
                "estudante@email.com",
                new Telefone(
                        "99",
                        "999999999",
                        true));

        this.turma = TurmaBuilder.build(
                "T-0001",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                Turno.INTEGRAL,
                CursoBuilder.build(
                        "C-X",
                        "Curso X",
                        40),
                SalaBuilder.build(
                        "Sala 01",
                        5));
    }

    @Test
    @DisplayName("Nao deveria matricular aluno em turma lotada")
    void cenario1() {
        turma = TurmaBuilder.buildFrom(turma, SalaBuilder.build("Sala 2", 1));

        turma.registrarMatricula(new Matricula(turma, estudante));
        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);

        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Matricula não realizada: turma está lotada!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria matricular aluno em turma com matriculas encerradas")
    void cenario2() {
        turma = TurmaBuilder.buildFrom(turma, turma.getDataInicio().minusDays(2), turma.getDataFim());

        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);

        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Matricula não realizada: turma com período de matriculas encerado!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria matricular aluno que ja esta matriculado na turma")
    void cenario3() {
        estudante.registrarMatricula(new Matricula(turma, estudante));

        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);
        given(estudanteRepository.findByCpf(dados.cpfEstudante())).willReturn(estudante);

        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Matricula não realizada: estudante já possui matricula para esta turma!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria matricular aluno que ja atingiu limite de turmas simultaneas")
    void cenario4() {
        var turma2 = TurmaBuilder.build(
                "T-0002",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                Turno.INTEGRAL,
                CursoBuilder.build(
                        "C-X",
                        "Curso X",
                        40),
                SalaBuilder.build(
                        "Sala 01",
                        2));

        var turma3 = TurmaBuilder.build(
                "T-0003",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                Turno.INTEGRAL,
                CursoBuilder.build(
                        "C-X",
                        "Curso X",
                        40),
                SalaBuilder.build(
                        "Sala 01",
                        2));

        estudante.registrarMatricula(new Matricula(turma, estudante));
        estudante.registrarMatricula(new Matricula(turma2, estudante));

        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma3);
        given(estudanteRepository.findByCpf(dados.cpfEstudante())).willReturn(estudante);

        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Matricula não realizada: estudante com limite de turmas em andamento!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria matricular aluno com uma ou nenhuma matricula")
    void cenario5() {
        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);
        given(estudanteRepository.findByCpf(dados.cpfEstudante())).willReturn(estudante);

        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(matriculaRepository).save(new Matricula(turma, estudante));

        var turma2 = TurmaBuilder.build(
                "T-0002",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                Turno.INTEGRAL,
                CursoBuilder.build(
                        "C-X",
                        "Curso X",
                        40),
                SalaBuilder.build(
                        "Sala 01",
                        2));

        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma2);
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(matriculaRepository).save(new Matricula(turma2, estudante));
    }

    @Test
    @DisplayName("Deveria aceitar matriculas no dia anterior ao inicio da turma")
    void cenario6() {
        turma = TurmaBuilder.buildFrom(turma, LocalDate.now().plusDays(1), turma.getDataFim());
        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);
        given(estudanteRepository.findByCpf(dados.cpfEstudante())).willReturn(estudante);

        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(matriculaRepository).save(new Matricula(turma, estudante));
    }

    @Test
    @DisplayName("Deveria aceitar matriculas no dia do inicio da turma")
    void cenario7() {
        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);
        given(estudanteRepository.findByCpf(dados.cpfEstudante())).willReturn(estudante);

        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(matriculaRepository).save(new Matricula(turma, estudante));
    }

    @Test
    @DisplayName("Deveria aceitar matriculas uma dia apos o inicio da turma")
    void cenario8() {
        turma = TurmaBuilder.buildFrom(turma, LocalDate.now().minusDays(1), turma.getDataFim());
        given(turmaRepository.findByCodigo(dados.codigoTurma())).willReturn(turma);
        given(estudanteRepository.findByCpf(dados.cpfEstudante())).willReturn(estudante);

        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(matriculaRepository).save(new Matricula(turma, estudante));
    }

}