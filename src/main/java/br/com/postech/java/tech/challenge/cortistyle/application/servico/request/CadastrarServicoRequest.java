package br.com.postech.java.tech.challenge.cortistyle.application.servico.request;


import br.com.postech.java.tech.challenge.cortistyle.domain.servico.entity.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CadastrarServicoRequest {

    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal valor;

    public Servico toServico() {
        return new Servico(null, this.descricao, this.valor);
    }
}
