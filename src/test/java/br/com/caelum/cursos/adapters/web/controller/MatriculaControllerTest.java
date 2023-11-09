package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.entity.EstudanteJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.MatriculaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.repository.EstudanteJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.MatriculaJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.TurmaJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosMatriculaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaRealizarMatriculaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaRealizarMatriculaPort;
import br.com.caelum.cursos.builders.EstudanteJpaEntityBuilder;
import br.com.caelum.cursos.builders.TurmaJpaEntityBuilder;
import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.core.estudante.Estudante;
import br.com.caelum.cursos.domain.core.matricula.Matricula;
import br.com.caelum.cursos.domain.core.turma.Turma;
import br.com.caelum.cursos.domain.ports.matricula.MatricularEstudanteEmTurmaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(MatriculaController.class)
class MatriculaControllerTest {

    private static final String URI = "/matriculas";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosParaRealizarMatriculaDto> jsonRequest;

    @Autowired
    private JacksonTester<DadosMatriculaDto> jsonResponse;

    @MockBean
    private MatricularEstudanteEmTurmaUseCase useCase;

    @MockBean
    private MatriculaJpaRepository repository;

    @MockBean
    private EstudanteJpaRepository estudanteRepository;

    @MockBean
    private TurmaJpaRepository turmaRepository;

    @Mock
    private Turma turma;

    @Mock
    private Estudante estudante;

    private EstudanteJpaEntity estudanteJpaEntity;
    private TurmaJpaEntity turmaJpaEntity;
    private DadosParaRealizarMatriculaDto dadosCadastro;

    @BeforeEach
    void setup() {
        estudanteJpaEntity = EstudanteJpaEntityBuilder.build();
        turmaJpaEntity = TurmaJpaEntityBuilder.build();
        dadosCadastro = new DadosParaRealizarMatriculaDto(estudanteJpaEntity.getId(), turmaJpaEntity.getId());

        lenient().when(turma.getCodigo()).thenReturn(turmaJpaEntity.getCodigo());
        lenient().when(estudante.getCpf()).thenReturn(new Cpf(estudanteJpaEntity.getCpf()));
    }

    @Test
    @DisplayName("Nao deveria realizar matricula com dados invalidos")
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
    @DisplayName("Deveria realizar matricula com dados validos")
    void cenario2() throws Exception {
        given(estudanteRepository.findById(estudanteJpaEntity.getId())).willReturn(Optional.ofNullable(estudanteJpaEntity));
        given(turmaRepository.findById(turmaJpaEntity.getId())).willReturn(Optional.ofNullable(turmaJpaEntity));

        var dadosParaRealizarMatriculaPort = new DadosParaRealizarMatriculaPort(dadosCadastro, estudanteJpaEntity, turmaJpaEntity);
        var matricula = new Matricula(turma, estudante);
        var entity = new MatriculaJpaEntity(50l, matricula.getCodigo(), estudanteJpaEntity, turmaJpaEntity);

        given(useCase.execute(dadosParaRealizarMatriculaPort)).willReturn(matricula);
        given(repository.findByCodigo(matricula.getCodigo())).willReturn(entity);

        var response = mvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        verify(useCase).execute(dadosParaRealizarMatriculaPort);
        verify(repository).save(new MatriculaJpaEntity(matricula.getCodigo(), estudanteJpaEntity, turmaJpaEntity));
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new DadosMatriculaDto(entity)).getJson());
        assertThat(response.getHeader("Location")).endsWith(String.format("%s/%d", URI, entity.getId()));
    }

}