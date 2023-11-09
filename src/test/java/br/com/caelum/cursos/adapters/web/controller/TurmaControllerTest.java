package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.entity.CursoJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.repository.CursoJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.SalaJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.TurmaJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaPort;
import br.com.caelum.cursos.adapters.web.dto.DadosTurmaDto;
import br.com.caelum.cursos.builders.CursoJpaEntityBuilder;
import br.com.caelum.cursos.builders.SalaJpaentityBuilder;
import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.core.turma.Turno;
import br.com.caelum.cursos.domain.ports.turma.AbrirTurmaUseCase;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(TurmaController.class)
class TurmaControllerTest {

    private static final String URI = "/turmas";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosParaAbrirTurmaDto> jsonRequest;

    @Autowired
    private JacksonTester<DadosTurmaDto> jsonResponse;

    @MockBean
    private AbrirTurmaUseCase useCase;

    @MockBean
    private TurmaJpaRepository repository;

    @MockBean
    private CursoJpaRepository cursoRepository;

    @MockBean
    private SalaJpaRepository salaRepository;

    private CursoJpaEntity cursoJpaEntity;
    private SalaJpaEntity salaJpaEntity;
    private DadosParaAbrirTurmaDto dadosCadastro;

    @BeforeEach
    void setup() {
        cursoJpaEntity = CursoJpaEntityBuilder.build();
        salaJpaEntity = SalaJpaentityBuilder.build();
        dadosCadastro = new DadosParaAbrirTurmaDto(
                "T-001",
                cursoJpaEntity.getId(),
                salaJpaEntity.getId(),
                Turno.INTEGRAL,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(6));

    }

    @Test
    @DisplayName("Nao deveria abrir turma com dados invalidos")
    void cenario1() throws Exception {
        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        verifyNoInteractions(useCase);
        verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("Deveria abrir turma com dados validos")
    void cenario2() throws Exception {
        given(cursoRepository.findById(cursoJpaEntity.getId())).willReturn(Optional.ofNullable(cursoJpaEntity));
        given(salaRepository.findById(salaJpaEntity.getId())).willReturn(Optional.ofNullable(salaJpaEntity));

        var dadosParaAbrirTurmaPort = new DadosParaAbrirTurmaPort(dadosCadastro, cursoJpaEntity, salaJpaEntity);
        var turma = new Turma(dadosParaAbrirTurmaPort);
        var entity = new TurmaJpaEntity(50l, turma.getCodigo(), cursoJpaEntity, salaJpaEntity, turma.getTurno(), turma.getDataInicio(), turma.getDataFim());

        given(useCase.execute(dadosParaAbrirTurmaPort)).willReturn(turma);
        given(repository.findByCodigo(turma.getCodigo())).willReturn(entity);

        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();


        verify(useCase).execute(dadosParaAbrirTurmaPort);
        verify(repository).save(new TurmaJpaEntity(turma, cursoJpaEntity, salaJpaEntity));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new DadosTurmaDto(entity)).getJson());
        assertThat(response.getHeader("Location")).endsWith(String.format("%s/%d", URI, entity.getId()));
    }

}