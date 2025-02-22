package br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.pagamento.servico.enums.TipoPagamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.infrastructure.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")
@SuppressWarnings("serial")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String username;
    private String password;
    private String token;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tpUsuario;
    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tpPagamento;

}
