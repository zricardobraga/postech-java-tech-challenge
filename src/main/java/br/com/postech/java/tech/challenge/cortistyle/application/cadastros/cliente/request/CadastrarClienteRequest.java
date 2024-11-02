package br.com.postech.java.tech.challenge.cortistyle.application.cadastros.cliente.request;


import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.TipoPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CadastrarClienteRequest {
    @NotBlank
    private String usuario;
    @NotNull
    private String senha;
    @NotBlank
    private String nome;
    @NotNull
    private String cpfCnpj;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Parameter(name = "Forma de pagamento", example = "NA_BARBEARIA, CARTAO_CADASTRADO")
    private TipoPagamentoEnum tipoPagamento;


    public Usuario toCliente() {
        return new Usuario(null, this.nome, this.cpfCnpj, this.usuario, this.senha, null, TipoUsuarioEnum.CLIENTE,
                this.tipoPagamento);
    }

}
