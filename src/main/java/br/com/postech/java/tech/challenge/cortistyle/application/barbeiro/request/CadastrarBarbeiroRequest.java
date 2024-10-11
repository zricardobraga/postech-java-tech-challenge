package br.com.postech.java.tech.challenge.cortistyle.application.barbeiro.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import lombok.Getter;

@Getter
public class CadastrarBarbeiroRequest {

    private String nome;
    private String cpfCnpj;
    private String gestorId;
    private Long filialId;

    public Usuario toBarbeiro() {
        return new Usuario(null, this.nome, this.cpfCnpj, TipoUsuarioEnum.BARBEIRO);
    }
}
