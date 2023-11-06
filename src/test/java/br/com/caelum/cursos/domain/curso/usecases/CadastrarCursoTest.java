package br.com.caelum.cursos.domain.curso.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.curso.Curso;
import br.com.caelum.cursos.domain.curso.ports.CursoRepository;
import br.com.caelum.cursos.domain.curso.ports.DadosParaCadastrarCurso;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarCursoTest {

    @InjectMocks
    private CadastrarCurso useCase;
    @Mock
    private CursoRepository repository;
    @Mock
    private DadosParaCadastrarCurso dados;

    @Test
    @DisplayName("Nao deveria cadastrar curso com codigo ja cadastrado")
    void cenario1() {
        given(dados.duracaoEmHoras()).willReturn(Duration.ofHours(10));
        given(repository.codigoJaCadastrado(dados.codigo())).willReturn(true);
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Código já utilizado em outro curso!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria cadastrar curso com carga horaria menor do que 4")
    void cenario2() {
        given(dados.duracaoEmHoras()).willReturn(Duration.ofHours(2));
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Duração mínima deve ser de 4 horas!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria cadastrar curso com carga horaria igual a 4")
    void cenario3() {
        given(dados.duracaoEmHoras()).willReturn(Duration.ofHours(4));
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).cadastrar(new Curso(dados));
    }

    @Test
    @DisplayName("Deveria cadastrar curso com carga horaria maior do que 4")
    void cenario4() {
        given(dados.duracaoEmHoras()).willReturn(Duration.ofHours(5));
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).cadastrar(new Curso(dados));
    }

}