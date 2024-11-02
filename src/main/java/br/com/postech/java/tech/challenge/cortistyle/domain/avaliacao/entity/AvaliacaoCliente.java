package br.com.postech.java.tech.challenge.cortistyle.domain.avaliacao.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity.AgendamentoServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_avaliacao_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agendamento_id", referencedColumnName = "id")
    private AgendamentoServico agendamento;

    @Column(name = "nota")
    private Integer nota;

    @Column(name = "comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuarioCadastrante;
}
