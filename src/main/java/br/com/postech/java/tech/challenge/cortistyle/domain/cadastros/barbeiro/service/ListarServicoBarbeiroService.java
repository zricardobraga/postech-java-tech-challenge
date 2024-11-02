package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response.ServicoBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroServico;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro.ServicoBarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarServicoBarbeiroService {

    private final ServicoBarbeiroRepository repository;

    public ArrayList<ServicoBarbeiroResponse> listar(Long barbeiroId) {
        return repository.findAllByBarbeiroId(barbeiroId)
                .stream()
                .map((BarbeiroServico barbeiro) ->
                        new ServicoBarbeiroResponse(
                                barbeiro.getServico().getId(),
                                barbeiro.getServico().getDescricao(),
                                barbeiro.getServico().getValor()
                        )
                ).collect(Collectors.toCollection(ArrayList::new));
    }
}
