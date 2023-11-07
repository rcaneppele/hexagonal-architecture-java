package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.repository.CursoJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosCursoDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarCursoDto;
import br.com.caelum.cursos.domain.ports.curso.CadastrarCursoUseCase;
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
@RequestMapping("cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CadastrarCursoUseCase useCase;
    private final CursoJpaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCursoDto> cadastrar(@RequestBody @Valid DadosParaCadastrarCursoDto dados, UriComponentsBuilder uriBuilder) {
        useCase.execute(dados);
        var entity = repository.findByCodigo(dados.codigo());
        var uri = uriBuilder.path("cursos/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCursoDto(entity));
    }

}
