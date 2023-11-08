package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.repository.EstudanteJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosEstudanteDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarEstudanteDto;
import br.com.caelum.cursos.adapters.web.dto.DadosTelefoneDto;
import br.com.caelum.cursos.builders.EstudanteJpaEntityBuilder;
import br.com.caelum.cursos.domain.ports.estudante.CadastrarEstudanteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(EstudanteController.class)
class EstudanteControllerTest {

    private static final String URI = "/estudantes";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosParaCadastrarEstudanteDto> jsonRequest;

    @Autowired
    private JacksonTester<DadosEstudanteDto> jsonResponse;

    @MockBean
    private CadastrarEstudanteUseCase useCase;

    @MockBean
    private EstudanteJpaRepository repository;

    private DadosParaCadastrarEstudanteDto dadosCadastro;

    @BeforeEach
    void setup() {
        dadosCadastro = new DadosParaCadastrarEstudanteDto(
                "Fulano da Silva",
                "000.000.000-00",
                LocalDate.of(1980, 10, 10),
                "fulano@email.com",
                new DadosTelefoneDto("00", "000000000", true));
    }

    @Test
    @DisplayName("Nao deveria cadastrar estudante com dados invalidos")
    void cenario1() throws Exception {
        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        verifyNoInteractions(useCase);
    }

    @Test
    @DisplayName("Deveria cadastrar estudante com dados validos")
    void cenario2() throws Exception {
        var entity = EstudanteJpaEntityBuilder.build(50l, dadosCadastro);
        given(repository.findByCpf(dadosCadastro.cpfAsString())).willReturn(entity);

        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        verify(useCase).execute(dadosCadastro);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new DadosEstudanteDto(entity)).getJson());
        assertThat(response.getHeader("Location")).endsWith(String.format("%s/%d", URI, entity.getId()));
    }

}