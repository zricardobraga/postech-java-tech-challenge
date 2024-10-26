package br.com.postech.java.tech.challenge.cortistyle.application.agendamento.pagamento.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SolicitaPagamentoRequest {

    private Usuario cliente;
    private BarbeiroServico servicoBarbeiro;
}
