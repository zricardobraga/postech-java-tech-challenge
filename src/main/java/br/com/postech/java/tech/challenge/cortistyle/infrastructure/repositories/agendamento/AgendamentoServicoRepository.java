package br.com.postech.java.tech.challenge.cortistyle.infrastructure.repositories.agendamento;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface AgendamentoServicoRepository extends JpaRepository<AgendamentoServico, Long> {

    ArrayList<AgendamentoServico> findAllByDataEqualsAndBarbeiroId(LocalDate data, Long barbeiroId);

}
