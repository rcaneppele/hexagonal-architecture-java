package br.com.caelum.cursos.domain.sala.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.sala.ports.DadosParaCadastrarSala;
import br.com.caelum.cursos.domain.sala.ports.SalaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarSalaTest {

    @InjectMocks
    private CadastrarSala useCase;
    @Mock
    private SalaRepository repository;
    @Mock
    private DadosParaCadastrarSala dados;

    @Test
    @DisplayName("Nao deveria cadastrar sala com nome ja cadastrado")
    void cenario1() {
        given(dados.capacidade()).willReturn(10);
        given(repository.nomeJaCadastrado(dados.nome())).willReturn(true);
        var ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Nome já utilizado em outra sala!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria cadastrar sala com capacidade menor do que 8")
    void cenario2() {
        given(dados.capacidade()).willReturn(5);
        var ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Capacidade mínima deve ser 8!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria cadastrar sala com capacidade igual a 8")
    void cenario3() {
        given(dados.capacidade()).willReturn(8);
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).cadastrar(dados);
    }

    @Test
    @DisplayName("Deveria cadastrar sala com capacidade maior do que 8")
    void cenario4() {
        given(dados.capacidade()).willReturn(10);
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).cadastrar(dados);
    }

}