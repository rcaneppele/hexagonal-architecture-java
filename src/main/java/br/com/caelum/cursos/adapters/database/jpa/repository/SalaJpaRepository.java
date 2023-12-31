package br.com.caelum.cursos.adapters.database.jpa.repository;

import br.com.caelum.cursos.adapters.database.jpa.entity.SalaJpaEntity;
import br.com.caelum.cursos.domain.core.sala.Sala;
import br.com.caelum.cursos.domain.ports.sala.SalaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaJpaRepository extends SalaRepository, JpaRepository<SalaJpaEntity, Long> {

    Boolean existsByNomeIgnoringCase(String nome);

    SalaJpaEntity findByNome(String nome);

    @Override
    default void cadastrar(Sala sala) {
        this.save(new SalaJpaEntity(sala));
    }

    @Override
    default Boolean nomeJaCadastrado(String nome) {
        return this.existsByNomeIgnoringCase(nome);
    }

}
