package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.pagamento;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.entity.PagamentoServicoAgendado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoServicoAgendado, Long> {

    Optional<PagamentoServicoAgendado> findByAgendamentoServico(AgendamentoServico agendamentoServico);

    ArrayList<PagamentoServicoAgendado> findByAgendamentoServicoBarbeiroId(Long barbeiroId);
}
