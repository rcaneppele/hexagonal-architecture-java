package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.repository.CursoJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosCursoDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarCursoDto;
import br.com.caelum.cursos.builders.CursoJpaEntityBuilder;
import br.com.caelum.cursos.domain.core.curso.Nivel;
import br.com.caelum.cursos.domain.ports.curso.CadastrarCursoUseCase;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(CursoController.class)
class CursoControllerTest {

    private static final String URI = "/cursos";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosParaCadastrarCursoDto> jsonRequest;

    @Autowired
    private JacksonTester<DadosCursoDto> jsonResponse;

    @MockBean
    private CadastrarCursoUseCase useCase;

    @MockBean
    private CursoJpaRepository repository;

    private DadosParaCadastrarCursoDto dadosCadastro;

    @BeforeEach
    void setup() {
        dadosCadastro = new DadosParaCadastrarCursoDto("xpto", "curso xpto", Nivel.BASICO, 10);
    }

    @Test
    @DisplayName("Nao deveria cadastrar curso com dados invalidos")
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
    @DisplayName("Deveria cadastrar curso com dados validos")
    void cenario2() throws Exception {
        var entity = CursoJpaEntityBuilder.build(50l, dadosCadastro);
        given(repository.findByCodigo(dadosCadastro.codigo())).willReturn(entity);

        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        verify(useCase).execute(dadosCadastro);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new DadosCursoDto(entity)).getJson());
        assertThat(response.getHeader("Location")).endsWith(String.format("%s/%d", URI, entity.getId()));
    }

}