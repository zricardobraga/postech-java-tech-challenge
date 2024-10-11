package br.com.postech.java.tech.challenge.cortistyle.domain.filial.service;

import br.com.postech.java.tech.challenge.cortistyle.application.filial.request.CadastrarFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.filial.response.CadastrarFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial.FilialRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarFilialService {

    private final FilialRepository repository;

    public CadastrarFilialResponse cadastrar(@Valid CadastrarFilialRequest request) {
        //TODO: identificar gestor por seu id e validá-lo.
        boolean gestorInvalido = false;

        if (gestorInvalido) {
            throw new PolicyException("Gestor inválido para cadastro de filiais.");
        }

        var filialCadastrada = repository.save(request.toFilial());

        return new CadastrarFilialResponse(filialCadastrada.getNome());
    }
}
