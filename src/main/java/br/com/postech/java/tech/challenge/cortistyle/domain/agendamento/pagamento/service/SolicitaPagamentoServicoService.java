package br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.pagamento.service;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.entity.PagamentoServicoAgendado;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.StatusPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.service.RealizarPagamentoService;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.pagamento.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SolicitaPagamentoServicoService {

    private final RealizarPagamentoService service;
    private final PagamentoRepository pagamentoRepository;

    public void solicitar(AgendamentoServico agendamentoServico) {

        if (pagamentoRepository.findByAgendamentoServico(agendamentoServico).isPresent()) {
            return;
        }

        PagamentoServicoAgendado pagamento = new PagamentoServicoAgendado();
        pagamento.setStatusPagamento(StatusPagamentoEnum.SOLICITADO);
        pagamento.setTpPagamento(agendamentoServico.getCliente().getTpPagamento());
        pagamento.setAgendamentoServico(agendamentoServico);
        pagamento.setValor(agendamentoServico.getServico().getServico().getValor());
        pagamentoRepository.save(pagamento);

        service.realizar(pagamento);
    }
}
