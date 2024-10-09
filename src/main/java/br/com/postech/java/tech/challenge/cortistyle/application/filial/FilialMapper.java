package br.com.postech.java.tech.challenge.cortistyle.application.filial;

import br.com.postech.java.tech.challenge.cortistyle.application.filial.request.CadastrarFilialRequest;
import br.com.postech.java.tech.challenge.cortistyle.application.filial.response.CadastrarFilialResponse;
import br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity.Filial;

public final class FilialMapper {

    FilialMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Filial paraFilial(CadastrarFilialRequest request) {
        return new Filial(null, request.getNome(), request.getCnpj());
    }

    public static CadastrarFilialResponse paraCadastrarFilialResponse(Filial filial) {
        return new CadastrarFilialResponse(filial.getNome());
    }
}
