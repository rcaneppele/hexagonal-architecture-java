package br.com.caelum.cursos.infra.spring;

import br.com.caelum.cursos.domain.core.curso.CadastrarCursoService;
import br.com.caelum.cursos.domain.core.estudante.CadastrarEstudanteService;
import br.com.caelum.cursos.domain.core.matricula.MatricularEstudanteEmTurmaService;
import br.com.caelum.cursos.domain.core.sala.CadastrarSalaService;
import br.com.caelum.cursos.domain.core.turma.AbrirTurmaService;
import br.com.caelum.cursos.domain.ports.curso.CursoRepository;
import br.com.caelum.cursos.domain.ports.estudante.EstudanteRepository;
import br.com.caelum.cursos.domain.ports.matricula.MatriculaRepository;
import br.com.caelum.cursos.domain.ports.sala.SalaRepository;
import br.com.caelum.cursos.domain.ports.turma.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseBeansConfiguration {

    private final SalaRepository salaRepository;
    private final CursoRepository cursoRepository;
    private final EstudanteRepository estudanteRepository;
    private final TurmaRepository turmaRepository;
    private final MatriculaRepository matriculaRepository;

    @Bean
    public CadastrarSalaService cadastrarSalaUseCase() {
        return new CadastrarSalaService(salaRepository);
    }

    @Bean
    public CadastrarCursoService cadastrarCursoUseCase() {
        return new CadastrarCursoService(cursoRepository);
    }

    @Bean
    public CadastrarEstudanteService cadastrarEstudanteUseCase() {
        return new CadastrarEstudanteService(estudanteRepository);
    }

    @Bean
    public AbrirTurmaService abrirTurmaUseCase() {
        return new AbrirTurmaService(turmaRepository);
    }

    @Bean
    public MatricularEstudanteEmTurmaService matricularEstudanteEmTurmaUseCase() {
        return new MatricularEstudanteEmTurmaService(turmaRepository, estudanteRepository, matriculaRepository);
    }

}
