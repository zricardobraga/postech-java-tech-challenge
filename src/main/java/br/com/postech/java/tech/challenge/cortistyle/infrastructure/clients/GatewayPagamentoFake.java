package br.com.postech.java.tech.challenge.cortistyle.infrastructure.clients;

import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.entity.PagamentoServicoAgendado;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.StatusPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.pagamento.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Component
public class GatewayPagamentoFake {

    private final PagamentoRepository repository;

    @Async
    public void aprovar(PagamentoServicoAgendado request) {
        log.info("Gateway de pagamento sendo chamado ...");
        boolean aprovado = false;

        try {
            Thread.sleep(60000);
            aprovado = true;
            log.info("Pagamento aprovado.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var status = aprovado ? StatusPagamentoEnum.REALIZADO : StatusPagamentoEnum.RECUSADO;

        request.setStatusPagamento(status);

        if (StatusPagamentoEnum.REALIZADO.equals(status)) {
            request.setDataHoraPagamento(LocalDateTime.now());
        }

        repository.save(request);

        // TODO: notifica barbeiro sobre pagamento aprovado
        // TODO: endpoint solicitando e cadastrando avaliacao sobre servico realizado
    }
}
