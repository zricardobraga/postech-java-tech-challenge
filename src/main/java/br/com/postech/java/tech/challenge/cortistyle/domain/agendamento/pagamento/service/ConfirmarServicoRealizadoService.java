package br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.pagamento.service;

import br.com.postech.java.tech.challenge.cortistyle.application.agendamento.pagamento.request.ConfirmaPagamentoRequest;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.enums.StatusAgendamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.agendamento.AgendamentoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConfirmarServicoRealizadoService {

    private final AgendamentoServicoRepository repository;
    private final SolicitaPagamentoServicoService solicitaPagamentoServico;

    public void confirmar(ConfirmaPagamentoRequest request) {
        var agendamentoEncontrado = repository.findById(request.getAgendamentoId());

        if (!agendamentoEncontrado.isPresent()) {
            throw new PolicyException("Agendamento Não econtrado.");
        }
        AgendamentoServico agendamentoServico = agendamentoEncontrado.get();
        Long barbeiroIdAgendamento = agendamentoServico.getBarbeiro().getId();
        Long barbeiroIdRequest = request.getBarbeiroId();

        if (!barbeiroIdAgendamento.equals(barbeiroIdRequest)) {
            throw new PolicyException("Barbeiro inválido para confirmar serviço realizado");
        }

        agendamentoServico.setStatus(StatusAgendamentoEnum.SERVICO_REALIZADO);

        repository.save(agendamentoServico);

        solicitaPagamentoServico.solicitar(agendamentoServico);
    }
}
