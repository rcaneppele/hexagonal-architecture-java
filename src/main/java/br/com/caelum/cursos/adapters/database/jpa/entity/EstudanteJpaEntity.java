package br.com.caelum.cursos.adapters.database.jpa.entity;

import br.com.caelum.cursos.domain.core.estudante.Estudante;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "estudantes")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class EstudanteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String  email;

    @Embedded
    private TelefoneVo celular;

    public EstudanteJpaEntity(Long id, String nome, String cpf, LocalDate dataDeNascimento, String email, TelefoneVo celular) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.celular = celular;
    }

    public EstudanteJpaEntity(Estudante estudante) {
        this(
                null,
                estudante.getNome(),
                estudante.getCpf().numero(),
                estudante.getDataDeNascimento(),
                estudante.getEmail().email(),
                new TelefoneVo(
                        estudante.getCelular().ddd(),
                        estudante.getCelular().numero(),
                        estudante.getCelular().whatsapp()));
    }

}
