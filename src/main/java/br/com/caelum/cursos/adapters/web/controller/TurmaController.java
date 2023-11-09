package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.entity.TurmaJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.repository.CursoJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.SalaJpaRepository;
import br.com.caelum.cursos.adapters.database.jpa.repository.TurmaJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaAbrirTurmaPort;
import br.com.caelum.cursos.adapters.web.dto.DadosTurmaDto;
import br.com.caelum.cursos.domain.ports.turma.AbrirTurmaUseCase;
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
@RequestMapping("turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final AbrirTurmaUseCase useCase;
    private final TurmaJpaRepository repository;
    private final CursoJpaRepository cursoRepository;
    private final SalaJpaRepository salaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosTurmaDto> cadastrar(@RequestBody @Valid DadosParaAbrirTurmaDto dados, UriComponentsBuilder uriBuilder) {
        var curso = cursoRepository.findById(dados.idCurso()).orElseThrow(() -> new EntityNotFoundException("Não existe curso cadastrado com o id informado!"));
        var sala = salaRepository.findById(dados.idSala()).orElseThrow(() -> new EntityNotFoundException("Não existe sala cadastrada com o id informado!"));

        var dadosParaAbrirTurmaPort = new DadosParaAbrirTurmaPort(dados, curso, sala);
        var turma = useCase.execute(dadosParaAbrirTurmaPort);

        // save tera que ser feito aqui e nao no repository, por conta de precisar carregar os relacionamentos
        var turmaEntity = new TurmaJpaEntity(turma, curso, sala);
        repository.save(turmaEntity);

        var entity = repository.findByCodigo(dados.codigo());
        var uri = uriBuilder.path("turmas/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosTurmaDto(entity));
    }

}
