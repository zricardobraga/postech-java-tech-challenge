package br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.StatusPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "tb_pagamento_servico_agendado")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class PagamentoServicoAgendado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraPagamento;

    private BigDecimal valor;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private AgendamentoServico agendamentoServico;

    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tpPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;

}
