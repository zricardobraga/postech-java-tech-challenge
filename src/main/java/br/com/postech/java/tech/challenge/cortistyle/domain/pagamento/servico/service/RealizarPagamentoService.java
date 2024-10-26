package br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.service;

import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.entity.PagamentoServicoAgendado;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.StatusPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.TipoPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.clients.GatewayPagamentoFake;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.pagamento.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RealizarPagamentoService {

    private final GatewayPagamentoFake gatewayPagamentoFake;
    private final PagamentoRepository pagamentoRepository;

    public StatusPagamentoEnum realizar(PagamentoServicoAgendado request) {
        request.setStatusPagamento(StatusPagamentoEnum.EM_ANALISE);

        if (TipoPagamentoEnum.NA_BARBEARIA.equals(request.getTpPagamento())) {
            request.setDataHoraPagamento(LocalDateTime.now());
            request.setStatusPagamento(StatusPagamentoEnum.REALIZADO);
        }

        // chamada assincrona
        gatewayPagamentoFake.aprovar(request);
        pagamentoRepository.save(request);

        return request.getStatusPagamento();
    }
}
