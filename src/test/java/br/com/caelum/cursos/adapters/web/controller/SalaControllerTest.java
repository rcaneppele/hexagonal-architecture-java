package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.repository.SalaJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarSalaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosSalaDto;
import br.com.caelum.cursos.domain.ports.sala.CadastrarSalaUseCase;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(SalaController.class)
class SalaControllerTest {

    private static final String URI = "/salas";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosParaCadastrarSalaDto> jsonRequest;

    @Autowired
    private JacksonTester<DadosSalaDto> jsonResponse;

    @MockBean
    private CadastrarSalaUseCase useCase;

    @MockBean
    private SalaJpaRepository repository;

    @Test
    @DisplayName("Nao deveria cadastrar sala com dados invalidos")
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
    @DisplayName("Deveria cadastrar sala com dados validos")
    void cenario2() throws Exception {
        var dadosCadastro = new DadosParaCadastrarSalaDto("Sala 1", 20);
        var salaJpaEntity = new SalaJpaEntity(50l, dadosCadastro.nome(), dadosCadastro.capacidade());
        given(repository.findByNome(dadosCadastro.nome())).willReturn(salaJpaEntity);

        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        verify(useCase).execute(dadosCadastro);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new DadosSalaDto(salaJpaEntity)).getJson());
        assertThat(response.getHeader("Location")).endsWith(String.format("%s/%d",URI,  salaJpaEntity.getId()));
    }

}