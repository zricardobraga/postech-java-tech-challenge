package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.filial.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.entity.Filial;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CadastrarFilialRequest {

    @NotBlank
    private String gestorId;
    @NotBlank
    private String nome;
    @NotBlank
    private String cnpj;

    public Filial toFilial() {
        return new Filial(null, this.nome, this.cnpj);
    }

}
