package br.com.caelum.cursos.infra.spring;

import br.com.caelum.cursos.domain.core.sala.CadastrarSalaService;
import br.com.caelum.cursos.domain.ports.sala.CadastrarSalaUseCase;
import br.com.caelum.cursos.domain.ports.sala.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseBeansConfiguration {

    private final SalaRepository salaRepository;

    @Bean
    public CadastrarSalaUseCase cadastrarSalaPort() {
        return new CadastrarSalaService(salaRepository);
    }

}
