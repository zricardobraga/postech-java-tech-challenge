package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.response.HorarioBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroHorario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro.BarbeiroHorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarHorariosBarbeiroService {

    private final BarbeiroHorarioRepository repository;

    public ArrayList<HorarioBarbeiroResponse> listar(Long barbeiroId) {
        return repository.findAllByBarbeiroId(barbeiroId)
                .stream()
                .map((BarbeiroHorario horario) ->
                        new HorarioBarbeiroResponse(
                                horario.getId(),
                                horario.getHorario()
                        )
                ).collect(Collectors.toCollection(ArrayList::new));
    }
}
