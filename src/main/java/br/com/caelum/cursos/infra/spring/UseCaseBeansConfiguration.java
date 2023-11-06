package br.com.caelum.cursos.infra.spring;

import br.com.caelum.cursos.domain.sala.ports.CadastrarSalaPort;
import br.com.caelum.cursos.domain.sala.ports.SalaRepository;
import br.com.caelum.cursos.domain.sala.usecases.CadastrarSala;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseBeansConfiguration {

    private final SalaRepository salaRepository;

    @Bean
    public CadastrarSalaPort cadastrarSalaPort() {
        return new CadastrarSala(salaRepository);
    }

}
