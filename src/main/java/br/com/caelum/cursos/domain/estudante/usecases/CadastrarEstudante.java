package br.com.caelum.cursos.domain.estudante.usecases;

import br.com.caelum.cursos.domain.RegraDeNegocioException;
import br.com.caelum.cursos.domain.estudante.Estudante;
import br.com.caelum.cursos.domain.estudante.ports.CadastrarEstudantePort;
import br.com.caelum.cursos.domain.estudante.ports.DadosParaCadastrarEstudante;
import br.com.caelum.cursos.domain.estudante.ports.EstudanteRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class CadastrarEstudante implements CadastrarEstudantePort {

    private final EstudanteRepository repository;

    public Estudante execute(DadosParaCadastrarEstudante dados) {
        validarEstudante(dados);
        var estudante = new Estudante(dados);
        repository.cadastrar(estudante);
        return estudante;
    }

    private void validarEstudante(DadosParaCadastrarEstudante dados) {
        if (Period.between(dados.dataDeNascimento(), LocalDate.now()).getYears() < 18) {
            throw new RegraDeNegocioException("Cadastro não realizado: Idade mínima é de 18 anos!");
        }

        var estudanteJaCadastrado = repository.CpfOuEmailJaCadastrado(dados.cpf(), dados.email());
        if (estudanteJaCadastrado) {
            throw new RegraDeNegocioException("Cadastro não realizado: CPF ou email já cadastrado para outro estudante!");
        }
    }

}
