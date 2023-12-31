package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.database.jpa.repository.SalaJpaRepository;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarSalaDto;
import br.com.caelum.cursos.adapters.web.dto.DadosSalaDto;
import br.com.caelum.cursos.domain.ports.sala.CadastrarSalaUseCase;
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
@RequestMapping("salas")
@RequiredArgsConstructor
public class SalaController {

    private final CadastrarSalaUseCase useCase;
    private final SalaJpaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosSalaDto> cadastrar(@RequestBody @Valid DadosParaCadastrarSalaDto dados, UriComponentsBuilder uriBuilder) {
        useCase.execute(dados);
        var entity = repository.findByNome(dados.nome());
        var uri = uriBuilder.path("salas/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosSalaDto(entity));
    }

}
