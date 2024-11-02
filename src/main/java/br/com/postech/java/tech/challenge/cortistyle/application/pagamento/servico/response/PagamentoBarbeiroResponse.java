package br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico.response;

import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.StatusPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.TipoPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@SuppressWarnings("serial")
public class PagamentoBarbeiroResponse implements Serializable {

    private Long id;
    private String nomeCliente;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh")
    private LocalDateTime data;
    private BigDecimal valor;
    private StatusPagamentoEnum statusPagamento;
    private TipoPagamentoEnum tipoPagamento;
}
