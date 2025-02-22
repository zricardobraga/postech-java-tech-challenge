package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.barbeiro.request;

import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CadastrarBarbeiroRequest {

    @NotBlank
    private String nome;
    @NotNull
    private String cpfCnpj;
    @NotNull
    private String gestorId;

    public Usuario toBarbeiro() {
        return new Usuario(null, this.nome, this.cpfCnpj, "", "", null, TipoUsuarioEnum.BARBEIRO, null);
    }
}
