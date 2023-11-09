package br.com.caelum.cursos.adapters.database.jpa.repository;

import br.com.caelum.cursos.adapters.database.jpa.entity.MatriculaJpaEntity;
import br.com.caelum.cursos.domain.core.matricula.Matricula;
import br.com.caelum.cursos.domain.ports.matricula.MatriculaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaJpaRepository extends MatriculaRepository, JpaRepository<MatriculaJpaEntity, Long> {

    MatriculaJpaEntity findByCodigo(String codigo);

    @Override
    default void registrar(Matricula matricula) {
        //nao da para salvar a matricula daqui, pois precisa carregar os relacionamentos =/
    }

}
