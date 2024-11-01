package br.com.postech.java.tech.challenge.cortistyle.domain.avaliacao.service;

import br.com.postech.java.tech.challenge.cortistyle.domain.avaliacao.entity.AvaliacaoCliente;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.entity.PagamentoServicoAgendado;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions.PolicyException;
import br.com.postech.java.tech.challenge.cortistyle.application.avaliacao.cliente.request.CadastrarAvaliacaoRequest;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.agendamento.AgendamentoServicoRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.avaliacao.AvaliacaoClientRepository;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.pagamento.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AvaliacaoService {

    private final AvaliacaoClientRepository repository;
    private final AgendamentoServicoRepository agendamentoServicoRepository;
    private final PagamentoRepository pagamentoServicoRepository;

    public void avaliar(CadastrarAvaliacaoRequest request) {

        var agendamento = agendamentoServicoRepository.findById(request.getAgendamentoId())
                .orElseThrow(() -> new PolicyException("Agendamento não encontrado"));

        var pagamento = pagamentoServicoRepository.findByAgendamentoServicoId(request.getAgendamentoId())
                .orElseThrow(() -> new PolicyException("Pagamento não encontrado"));

        if (!"REALIZADO".equals(pagamento.getStatusPagamento())) {
            throw new PolicyException("Pagamento não realizado");
        }

        AvaliacaoCliente avaliacao = new AvaliacaoCliente();
        avaliacao.setAgendamento(agendamento);
        avaliacao.setNota(request.getNota());
        avaliacao.setComentario(request.getComentario());
        avaliacao.setUsuarioCadastrante(agendamento.getCliente());

        repository.save(avaliacao);

    }
}
