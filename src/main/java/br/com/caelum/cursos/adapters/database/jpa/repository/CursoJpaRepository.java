package br.com.caelum.cursos.adapters.database.jpa.repository;

import br.com.caelum.cursos.adapters.database.jpa.entity.CursoJpaEntity;
import br.com.caelum.cursos.domain.core.curso.Curso;
import br.com.caelum.cursos.domain.ports.curso.CursoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoJpaRepository extends CursoRepository, JpaRepository<CursoJpaEntity, Long> {

    Boolean existsByCodigoIgnoringCase(String codigo);

    CursoJpaEntity findByCodigo(String codigo);


    @Override
    default void cadastrar(Curso curso) {
        this.save(new CursoJpaEntity(curso));
    }

    @Override
    default Boolean codigoJaCadastrado(String codigo) {
        return this.existsByCodigoIgnoringCase(codigo);
    }

}
