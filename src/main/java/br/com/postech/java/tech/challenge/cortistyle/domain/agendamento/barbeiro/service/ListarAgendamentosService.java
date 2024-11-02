package br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.barbeiro.service;

import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.reponse.BarbeiroAgendamentoResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.reponse.BarbeiroHorarioAgendaResponse;
import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.barbeiro.request.ListarAgendamentoBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.enums.StatusAgendamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroHorario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.agendamento.AgendamentoServicoRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.cadastros.barbeiro.BarbeiroHorarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ListarAgendamentosService {

    private final AgendamentoServicoRepository agendamentoServicoRepository;
    private final BarbeiroHorarioRepository barbeiroHorarioRepository;

    public ArrayList<BarbeiroHorarioAgendaResponse> listarHorarios(ListarAgendamentoBarbeiroRequest request) {

        var horariosBarbeiro = barbeiroHorarioRepository.findAllByBarbeiroId(request.getBarbeiroId());

        if (horariosBarbeiro.isEmpty()) {
            throw new PolicyException("Barbeiro não possui horários cadastrados");
        }

        var agendamentosBarbeiro = agendamentoServicoRepository.findAllByDataEqualsAndBarbeiroId(request.getData(),
                        request.getBarbeiroId())
                .stream()
                .filter(agendamento -> !agendamento.getStatus().equals(StatusAgendamentoEnum.CANCELADO))
                .map(AgendamentoServico::getHorarioBarbeiro)
                .collect(Collectors.toCollection(ArrayList::new));

        // não há agendamentos para o barbeiro na da solicitada
        // retorna todos horários barbeiro
        if (agendamentosBarbeiro.isEmpty()) {
            return toResponse(request, horariosBarbeiro);
        }

        List<Long> idsBarbeiroHorarioAgendados = agendamentosBarbeiro.stream()
                .map(BarbeiroHorario::getId)
                .collect(Collectors.toList());

        for (BarbeiroHorario horario : horariosBarbeiro) {
            if (idsBarbeiroHorarioAgendados.contains(horario.getId())) {
                horario.setAgendado(true);
            }
        }

        return toResponse(request, horariosBarbeiro);
    }

    private static ArrayList<BarbeiroHorarioAgendaResponse> toResponse(ListarAgendamentoBarbeiroRequest request,
                                                                       List<BarbeiroHorario> horariosBarbeiro) {
        return horariosBarbeiro.stream()
                .map(horario -> new BarbeiroHorarioAgendaResponse(
                        horario.getId(),
                        horario.getHorario(),
                        horario.getAgendado(),
                        request.getData()
                )).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<BarbeiroAgendamentoResponse> listarAgendamentos(ListarAgendamentoBarbeiroRequest request) {
        return agendamentoServicoRepository.findAllByDataEqualsAndBarbeiroId(request.getData(), request.getBarbeiroId())
                .stream()
                .map(agendamento -> new BarbeiroAgendamentoResponse(
                        agendamento.getId(),
                        agendamento.getStatus(),
                        agendamento.getData()
                ))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
