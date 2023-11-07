package br.com.caelum.cursos.domain.core.curso;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.ports.curso.CursoRepository;
import br.com.caelum.cursos.domain.ports.curso.DadosParaCadastrarCurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarCursoServiceTest {

    @InjectMocks
    private CadastrarCursoService service;
    @Mock
    private CursoRepository repository;
    @Mock
    private DadosParaCadastrarCurso dados;

    @BeforeEach
    void setup() {
        lenient().when(dados.codigo()).thenReturn("cs-xpto");
        lenient().when(dados.nome()).thenReturn("Curso xpto");
        lenient().when(dados.nivel()).thenReturn(Nivel.BASICO);
        lenient().when(dados.duracaoEmHoras()).thenReturn(Duration.ofHours(10));
    }

    @Test
    @DisplayName("Nao deveria cadastrar curso com codigo ja cadastrado")
    void cenario1() {
        given(repository.codigoJaCadastrado(dados.codigo())).willReturn(true);
        var ex = assertThrows(RegraDeNegocioException.class, () -> service.execute(dados));
        assertEquals("Cadastro não realizado: Código já utilizado em outro curso!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria cadastrar curso com carga horaria menor do que 4")
    void cenario2() {
        given(dados.duracaoEmHoras()).willReturn(Duration.ofHours(2));
        var ex = assertThrows(RegraDeNegocioException.class, () -> service.execute(dados));
        assertEquals("Cadastro não realizado: Duração mínima deve ser de 4 horas!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria cadastrar curso com carga horaria igual a 4")
    void cenario3() {
        given(dados.duracaoEmHoras()).willReturn(Duration.ofHours(4));
        var curso = assertDoesNotThrow(() -> service.execute(dados));
        verify(repository).cadastrar(curso);
    }

    @Test
    @DisplayName("Deveria cadastrar curso com carga horaria maior do que 4")
    void cenario4() {
        var curso = assertDoesNotThrow(() -> service.execute(dados));
        verify(repository).cadastrar(curso);
    }

}