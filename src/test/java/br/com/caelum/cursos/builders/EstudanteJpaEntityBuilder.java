package br.com.caelum.cursos.builders;

import br.com.caelum.cursos.adapters.database.jpa.entity.EstudanteJpaEntity;
import br.com.caelum.cursos.adapters.database.jpa.entity.TelefoneVo;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarEstudanteDto;

import java.time.LocalDate;

public class EstudanteJpaEntityBuilder {

    public static EstudanteJpaEntity build(Long id, String nome, String cpf, LocalDate dataDeNascimento, String email, TelefoneVo telefone) {
        return new EstudanteJpaEntity(id, nome, cpf, dataDeNascimento, email, telefone);
    }

    public static EstudanteJpaEntity build() {
        return build(
                50l,
                "Fulano da Silva",
                "000.000.000-00",
                LocalDate.of(1980, 10, 10),
                "fulano@email.com",
                new TelefoneVo("99", "999999999", true));
    }

    public static EstudanteJpaEntity build(Long id, DadosParaCadastrarEstudanteDto dadosCadastro) {
        return build(id, dadosCadastro.nome(), dadosCadastro.cpfAsString(), dadosCadastro.dataDeNascimento(), dadosCadastro.emailAsString(), new TelefoneVo(dadosCadastro.celular()));
    }

}
