package br.com.caelum.cursos.domain.sala.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.sala.Sala;
import br.com.caelum.cursos.domain.sala.ports.CadastrarSalaPort;
import br.com.caelum.cursos.domain.sala.ports.DadosParaCadastrarSala;
import br.com.caelum.cursos.domain.sala.ports.SalaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarSala implements CadastrarSalaPort {

    private final SalaRepository repository;

    public Sala execute(DadosParaCadastrarSala dados) {
        validarSala(dados);
        return repository.cadastrar(dados);
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
