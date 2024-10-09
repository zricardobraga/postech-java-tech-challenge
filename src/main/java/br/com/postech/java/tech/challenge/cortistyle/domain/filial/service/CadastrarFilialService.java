package br.com.postech.java.tech.challenge.cortistyle.domain.filial.service;

import br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity.Filial;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarFilialService {

    private final FilialRepository repository;

    public Filial cadastrar(Filial novaFilial, String gestorId) {
        //TODO: identificar gestor por seu id e validá-lo.
        boolean gestorInvalido = false;

        if (gestorInvalido) {
            throw new PolicyException("Gestor inválido para cadastro de filiais.");
        }
        return repository.save(novaFilial);
    }
}
