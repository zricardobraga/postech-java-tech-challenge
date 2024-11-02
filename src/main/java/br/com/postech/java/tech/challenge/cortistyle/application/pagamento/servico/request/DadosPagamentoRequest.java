package br.com.postech.java.tech.challenge.cortistyle.application.pagamento.servico.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.TipoPagamentoEnum;
import lombok.Getter;

@Getter
public class DadosPagamentoRequest {

    private Long agendamentoId;
    private TipoPagamentoEnum tpPagamento;
    private String nomeCartao;
    private String numeroCartao;
    private String vencimentoCartao;
    private Integer cvc;

}
