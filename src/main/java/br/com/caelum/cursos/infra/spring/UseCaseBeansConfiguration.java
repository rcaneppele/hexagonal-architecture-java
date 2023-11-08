package br.com.caelum.cursos.infra.spring;

import br.com.caelum.cursos.domain.core.curso.CadastrarCursoService;
import br.com.caelum.cursos.domain.core.sala.CadastrarSalaService;
import br.com.caelum.cursos.domain.ports.curso.CursoRepository;
import br.com.caelum.cursos.domain.ports.sala.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseBeansConfiguration {

    private final SalaRepository salaRepository;
    private final CursoRepository cursoRepository;

    @Bean
    public CadastrarSalaService cadastrarSalaUseCase() {
        return new CadastrarSalaService(salaRepository);
    }

    @Bean
    public CadastrarCursoService cadastrarCursoUseCase() {
        return new CadastrarCursoService(cursoRepository);
    }

}
