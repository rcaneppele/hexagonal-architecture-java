package br.com.caelum.cursos.domain.turma.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.turma.ports.DadosParaAbrirTurma;
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
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AbrirTurmaTest {

    @InjectMocks
    private AbrirTurma useCase;
    @Mock
    private TurmaRepository repository;
    @Mock
    private DadosParaAbrirTurma dados;

    @BeforeEach
    void setup() {
        given(dados.dataInicio()).willReturn(LocalDate.now());
        lenient().when(dados.dataFim()).thenReturn(LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("Nao deveria abrir turma com codigo ja cadastrado")
    void cenario1() {
        given(repository.codigoJaCadastrado(dados.codigo())).willReturn(true);
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Código já utilizado em outra turma!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria abrir turma com data de inicio menor do que hoje")
    void cenario2() {
        given(dados.dataInicio()).willReturn(LocalDate.now().minusDays(1));
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Data de início não pode ser anterior a hoje!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria abrir turma com data de fim menor do que data de inicio")
    void cenario3() {
        given(dados.dataFim()).willReturn(LocalDate.now().minusDays(1));
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Data fim deve ser posterior a data de início!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria abrir turma com sala ja ocupada por outra turma")
    void cenario4() {
        given(repository.salaJaOcupadaNoPeriodo(dados.sala(), dados.dataInicio(), dados.dataFim())).willReturn(true);
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Sala já utilizada por outra turma no mesmo período!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria abrir turma com curso que ja atingiu limite de turmas em andamento")
    void cenario5() {
        given(repository.quantidadeDeTurmasEmAbertoDoCurso(dados.curso())).willReturn(4);
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Curso atingiu limite de 4 turmas em andamento!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria abrir turma com sala desocupada")
    void cenario6() {
        given(repository.salaJaOcupadaNoPeriodo(dados.sala(), dados.dataInicio(), dados.dataFim())).willReturn(false);
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).abrir(dados);
    }

    @Test
    @DisplayName("Deveria abrir turma no limite de turmas")
    void cenario7() {
        given(repository.quantidadeDeTurmasEmAbertoDoCurso(dados.curso())).willReturn(3);
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).abrir(dados);
    }

    @Test
    @DisplayName("Deveria abrir turma abaixo do limite de turmas")
    void cenario8() {
        given(repository.quantidadeDeTurmasEmAbertoDoCurso(dados.curso())).willReturn(2);
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).abrir(dados);
    }

}