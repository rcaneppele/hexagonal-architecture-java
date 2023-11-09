package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.entity.MatriculaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.repository.EstudanteJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.MatriculaJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.TurmaJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosMatriculaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaRealizarMatriculaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaRealizarMatriculaPort;
import br.com.caelum.cursos.domain.ports.matricula.MatricularEstudanteEmTurmaUseCase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatricularEstudanteEmTurmaUseCase useCase;
    private final EstudanteJpaRepository estudanteRepository;
    private final TurmaJpaRepository turmaRepository;
    private final MatriculaJpaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMatriculaDto> cadastrar(@RequestBody @Valid DadosParaRealizarMatriculaDto dados, UriComponentsBuilder uriBuilder) {
        var estudante = estudanteRepository.findById(dados.idEstudante()).orElseThrow(() -> new EntityNotFoundException("Não existe estudante cadastrado com o id informado!"));
        var turma = turmaRepository.findById(dados.idTurma()).orElseThrow(() -> new EntityNotFoundException("Não existe turma cadastrada com o id informado!"));

        var dadosParaRealizarMatriculaPort = new DadosParaRealizarMatriculaPort(dados, estudante, turma);
        var matricula = useCase.execute(dadosParaRealizarMatriculaPort);

        // save tera que ser feito aqui e nao no repository, por conta de precisar carregar os relacionamentos
        var matriculaEntity = new MatriculaJpaEntity(matricula.getCodigo(), estudante, turma);
        repository.save(matriculaEntity);

        var entity = repository.findByCodigo(matriculaEntity.getCodigo());
        var uri = uriBuilder.path("matriculas/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosMatriculaDto(entity));
    }

}
