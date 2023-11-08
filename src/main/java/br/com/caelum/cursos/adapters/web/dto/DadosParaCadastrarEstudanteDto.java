package br.com.caelum.cursos.adapters.web.dto;

import br.com.caelum.cursos.adapters.database.jpa.entity.EstudanteJpaEntity;
import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.core.estudante.Email;
import br.com.caelum.cursos.domain.core.estudante.Telefone;
import br.com.caelum.cursos.domain.ports.estudante.DadosParaCadastrarEstudante;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosParaCadastrarEstudanteDto(
        @NotBlank(message = "Nome do estudante é obrigatório!")
        String nome,

        @NotBlank(message = "CPF do curso é obrigatório!")
        @JsonAlias("cpf")
        String cpfAsString,

        @NotNull(message = "Data de nascimento do curso é obrigatória!")
        LocalDate dataDeNascimento,

        @NotBlank(message = "Email do estudante é obrigatório!")
        @jakarta.validation.constraints.Email(message = "Email em formato inválido!")
        @JsonAlias("email")
        String emailAsString,

        @NotNull(message = "Celular do estudante é obrigatório!")
        @JsonAlias("celular")
        DadosTelefoneDto celularDto
        ) implements DadosParaCadastrarEstudante {

        public DadosParaCadastrarEstudanteDto(EstudanteJpaEntity entity) {
                this(
                        entity.getNome(),
                        entity.getCpf(),
                        entity.getDataDeNascimento(),
                        entity.getEmail(),
                        new DadosTelefoneDto(
                                entity.getCelular().getDdd(),
                                entity.getCelular().getNumero(),
                                entity.getCelular().getWhatsapp()));
        }

        @Override
        public Email email() {
                return new Email(emailAsString);
        }

        @Override
        public Cpf cpf() {
                return new Cpf(cpfAsString);
        }

        @Override
        public Telefone celular() {
                return new Telefone(celularDto.ddd(), celularDto.numero(), celularDto().whatsapp());
        }

}
