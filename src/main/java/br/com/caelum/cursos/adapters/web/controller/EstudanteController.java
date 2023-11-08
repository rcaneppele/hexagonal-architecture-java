package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.repository.EstudanteJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosEstudanteDto;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarEstudanteDto;
import br.com.caelum.cursos.domain.ports.estudante.CadastrarEstudanteUseCase;
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
@RequestMapping("estudantes")
@RequiredArgsConstructor
public class EstudanteController {

    private final CadastrarEstudanteUseCase useCase;
    private final EstudanteJpaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosEstudanteDto> cadastrar(@RequestBody @Valid DadosParaCadastrarEstudanteDto dados, UriComponentsBuilder uriBuilder) {
        useCase.execute(dados);
        var entity = repository.findByCpf(dados.cpf().numero());
        var uri = uriBuilder.path("estudantes/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosEstudanteDto(entity));
    }

}
