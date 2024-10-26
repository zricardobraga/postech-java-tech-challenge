package br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.service;

import br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico.request.PagamentoBarbeiroRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico.response.PagamentoBarbeiroResponse;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.pagamento.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ListaPagamentoBarbeiroService {

    private final PagamentoRepository repository;

    public ArrayList<PagamentoBarbeiroResponse> listar(PagamentoBarbeiroRequest request) {
        return repository.findByAgendamentoServicoBarbeiroId(request.getBarbeiroId())
                .stream()
                .map(pagamento -> PagamentoBarbeiroResponse.builder()
                        .id(pagamento.getId())
                        .valor(pagamento.getValor())
                        .data(pagamento.getDataHoraPagamento())
                        .statusPagamento(pagamento.getStatusPagamento())
                        .nomeCliente(pagamento.getAgendamentoServico().getCliente().getNome())
                        .tipoPagamento(pagamento.getTpPagamento())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
