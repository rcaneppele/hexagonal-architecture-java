package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.EstudanteJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.TelefoneVo;

import java.time.LocalDate;

public record DadosEstudanteDto(Long id, String nome, String cpf, LocalDate dataDeNascimento, String email, TelefoneVo celular) {

    public DadosEstudanteDto(EstudanteJpaEntity entity) {
        this(entity.getId(), entity.getNome(), entity.getCpf(), entity.getDataDeNascimento(), entity.getEmail(), entity.getCelular());
    }

}
