package br.com.caelum.cursos.domain.core.sala;

import br.com.caelum.cursos.domain.core.RegraDeNegocioException;
import br.com.caelum.cursos.domain.ports.sala.CadastrarSalaUseCase;
import br.com.caelum.cursos.domain.ports.sala.DadosParaCadastrarSala;
import br.com.caelum.cursos.domain.ports.sala.SalaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarSalaService implements CadastrarSalaUseCase {

    private final SalaRepository repository;

    public Sala execute(DadosParaCadastrarSala dados) {
        validarSala(dados);
        var sala = new Sala(dados);
        repository.cadastrar(sala);
        return sala;
    }

    private void validarSala(DadosParaCadastrarSala dados) {
        if (dados.capacidade() < 8) {
            throw new RegraDeNegocioException("Cadastro não realizado: Capacidade mínima deve ser 8!");
        }

        var salaJaCadastrada = repository.nomeJaCadastrado(dados.nome());
        if (salaJaCadastrada) {
            throw new RegraDeNegocioException("Cadastro não realizado: Nome já utilizado em outra sala!");
        }
    }

}
