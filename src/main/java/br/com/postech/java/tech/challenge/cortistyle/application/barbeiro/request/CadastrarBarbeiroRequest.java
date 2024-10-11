package br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity.Barbeiro;
import lombok.Getter;

@Getter
public class CadastrarBarbeiroRequest {

    private String nome;
    private String cpfCnpj;

    private String gestorId;
    private Long filialId;

    public Barbeiro toBarbeiro() {
        return new Barbeiro(null, this.nome, this.cpfCnpj);
    }
}
