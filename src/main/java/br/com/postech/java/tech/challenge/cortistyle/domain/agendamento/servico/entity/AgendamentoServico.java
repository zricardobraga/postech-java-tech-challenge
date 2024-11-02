package br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.agendamento.servico.enums.StatusAgendamentoEnum;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroHorario;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity.BarbeiroServico;
import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.entity.Filial;
import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "tb_agendamento_servico")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class AgendamentoServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusAgendamentoEnum status;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Usuario barbeiro;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Filial filial;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private BarbeiroServico servico;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private BarbeiroHorario horarioBarbeiro;

}
