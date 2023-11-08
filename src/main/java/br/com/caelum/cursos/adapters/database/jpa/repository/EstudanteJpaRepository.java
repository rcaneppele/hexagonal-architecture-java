package br.com.caelum.cursos.adapters.database.jpa.repository;

import br.com.caelum.cursos.adapters.database.jpa.entity.EstudanteJpaEntity;
import br.com.caelum.cursos.adapters.web.dto.DadosParaCadastrarEstudanteDto;
import br.com.caelum.cursos.domain.core.estudante.Cpf;
import br.com.caelum.cursos.domain.core.estudante.Email;
import br.com.caelum.cursos.domain.core.estudante.Estudante;
import br.com.caelum.cursos.domain.ports.estudante.EstudanteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteJpaRepository extends EstudanteRepository, JpaRepository<EstudanteJpaEntity, Long> {

    EstudanteJpaEntity findByCpf(String cpf);

    Boolean existsByCpfOrEmailIgnoringCase(String cpf, String email);

    @Override
    default void cadastrar(Estudante estudante) {
        this.save(new EstudanteJpaEntity(estudante));
    }

    @Override
    default Estudante buscarPorCpf(Cpf cpf) {
        var entity = this.findByCpf(cpf.numero());
        var dados = new DadosParaCadastrarEstudanteDto(entity);
        return new Estudante(dados);
    }

    @Override
    default Boolean CpfOuEmailJaCadastrado(Cpf cpf, Email email) {
        return this.existsByCpfOrEmailIgnoringCase(cpf.numero(), email.email());
    }

}
