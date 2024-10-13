package br.com.postech.java.tech.challenge.cortistyle.application.filial.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity.BarbeiroFilial;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IncluirBarbeiroFilialRequest {

    @NotNull
    private Long barbeiroId;
    @NotNull
    private Long filialId;
    @NotNull
    private Long gestorId;

    public BarbeiroFilial toFilialBarbeiro() {
        return new BarbeiroFilial(null, this.filialId, null);
    }
}
