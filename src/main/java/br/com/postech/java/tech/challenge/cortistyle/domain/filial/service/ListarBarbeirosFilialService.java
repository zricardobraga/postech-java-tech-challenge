package br.com.postech.java.tech.challenge.cortistyle.domain.filial.service;

import br.com.postech.java.tech.challenge.cortistyle.application.filial.response.BarbeiroFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.filial.FilialBarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarBarbeirosFilialService {

    private final FilialBarbeiroRepository repository;

    public List<BarbeiroFilialResponse> listarBarbeirosBy(Long filialId) {
        List<BarbeiroFilialResponse> barbeirosDaFilial = repository.findAllByFilialId(filialId)
                .stream()
                .map(elem -> new BarbeiroFilialResponse(elem.getBarbeiro().getId(), elem.getBarbeiro().getNome()))
                .collect(Collectors.toList());

        return barbeirosDaFilial;
    }
}
