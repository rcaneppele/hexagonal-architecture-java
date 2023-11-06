package br.com.caelum.cursos.domain.estudante.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.estudante.ports.DadosParaCadastrarEstudante;
import br.com.caelum.cursos.domain.estudante.ports.EstudanteRepository;
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
class CadastrarEstudanteTest {

    @InjectMocks
    private CadastrarEstudante useCase;
    @Mock
    private EstudanteRepository repository;
    @Mock
    private DadosParaCadastrarEstudante dados;

    @Test
    @DisplayName("Nao deveria cadastrar estudante com cpf ou email ja cadastrado")
    void cenario1() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(20));
        given(repository.existsByCpfOrEmail(dados.cpf(), dados.email())).willReturn(true);
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: CPF ou email já cadastrado para outro estudante!", ex.getMessage());
    }

    @Test
    @DisplayName("Nao deveria cadastrar estudante menor de idade")
    void cenario2() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(15));
        Exception ex = assertThrows(RegraDeNegocioException.class, () -> useCase.execute(dados));
        assertEquals("Cadastro não realizado: Idade mínima é de 18 anos!", ex.getMessage());
    }

    @Test
    @DisplayName("Deveria cadastrar estudante com 18 anos")
    void cenario3() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(18));
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).save(new Estudante(dados));
    }

    @Test
    @DisplayName("Deveria cadastrar estudante com mais de 18 anos")
    void cenario4() {
        given(dados.dataDeNascimento()).willReturn(LocalDate.now().minusYears(20));
        assertDoesNotThrow(() -> useCase.execute(dados));
        verify(repository).save(new Estudante(dados));
    }

}