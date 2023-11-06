package br.com.caelum.cursos.adapters.web.controller;

import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarSalaDto;
import br.com.caelum.cursos.domain.sala.ports.CadastrarSalaPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("salas")
@RequiredArgsConstructor
public class SalaController {

    private final CadastrarSalaPort port;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosParaCadastrarSalaDto dados) {
        port.execute(dados);
        return ResponseEntity.ok("Sala cadastrada com sucesso!");
    }

}
