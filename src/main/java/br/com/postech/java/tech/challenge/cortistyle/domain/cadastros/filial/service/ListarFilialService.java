package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.service;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.response.FilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.filial.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ListarFilialService {

    private final FilialRepository repository;

    public ArrayList<FilialResponse> listar() {
        return repository.findAll().stream()
                .map(filial -> new FilialResponse(filial.getId(), filial.getNome()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
