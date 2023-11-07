package br.com.caelum.cursos.domain.core.estudante;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.ports.estudante.DadosParaCadastrarEstudante;
import br.com.caelum.cursos.domain.ports.estudante.EstudanteRepository;
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
class CadastrarEstudanteServiceTest {

    @InjectMocks
    private CadastrarEstudanteService service;
    @Mock
    private EstudanteRepository repository;
    @Mock
    private DadosParaCadastrarEstudante dados;

    @BeforeEach
    void setup() {
        lenient().when(dados.nome()).thenReturn("Estudante");
        lenient().when(dados.cpf()).thenReturn(new Cpf("000.000.00-00"));
        lenient().when(dados.email()).thenReturn(new Email("estudante@email.com.br"));
        lenient().when(dados.dataDeNascimento()).thenReturn(LocalDate.of(1980, 10, 10));
        lenient().when(dados.celular()).thenReturn(new Telefone("99", "999999999", true));
    }

    @Test
    @DisplayName("Nao deveria cadastrar estudante com cpf ou email ja cadastrado")
    void cenario1() {
        given(repository.CpfOuEmailJaCadastrado(dados.cpf(), dados.email())).willReturn(true);
        var ex = assertThrows(RegraDeNegocioException.class, () -> service.execute(dados));
        assertEquals("Cadastro não realizado: CPF ou email já cadastrado para outro estudante!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria cadastrar estudante menor de idade")
    void cenario2() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(15));
        var ex = assertThrows(RegraDeNegocioException.class, () -> service.execute(dados));
        assertEquals("Cadastro não realizado: Idade mínima é de 18 anos!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria cadastrar estudante com 18 anos")
    void cenario3() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(18));
        var estudante = assertDoesNotThrow(() -> service.execute(dados));
        verify(repository).cadastrar(estudante);
    }

    @Test
    @DisplayName("Deveria cadastrar estudante com mais de 18 anos")
    void cenario4() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(20));
        var estudante = assertDoesNotThrow(() -> service.execute(dados));
        verify(repository).cadastrar(estudante);
    }

}