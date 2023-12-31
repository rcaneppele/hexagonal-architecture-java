package br.com.caelum.cursos.domain.core.sala;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.ports.sala.DadosParaCadastrarSala;
import br.com.caelum.cursos.domain.ports.sala.SalaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarSalaServiceTest {

    @InjectMocks
    private CadastrarSalaService service;
    @Mock
    private SalaRepository repository;
    @Mock
    private DadosParaCadastrarSala dados;

    @BeforeEach
    void setup() {
        lenient().when(dados.nome()).thenReturn("Sala 1");
        lenient().when(dados.capacidade()).thenReturn(20);
    }

    @Test
    @DisplayName("Nao deveria cadastrar sala com nome ja cadastrado")
    void cenario1() {
        given(repository.nomeJaCadastrado(dados.nome())).willReturn(true);
        var ex = assertThrows(RegraDeNegocioException.class, () -> service.execute(dados));
        assertEquals("Cadastro não realizado: Nome já utilizado em outra sala!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria cadastrar sala com capacidade menor do que 8")
    void cenario2() {
        given(dados.capacidade()).willReturn(5);
        var ex = assertThrows(RegraDeNegocioException.class, () -> service.execute(dados));
        assertEquals("Cadastro não realizado: Capacidade mínima deve ser 8!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria cadastrar sala com capacidade igual a 8")
    void cenario3() {
        given(dados.capacidade()).willReturn(8);
        var sala = assertDoesNotThrow(() -> service.execute(dados));
        verify(repository).cadastrar(sala);
    }

    @Test
    @DisplayName("Deveria cadastrar sala com capacidade maior do que 8")
    void cenario4() {
        var sala = assertDoesNotThrow(() -> service.execute(dados));
        verify(repository).cadastrar(sala);
    }

}