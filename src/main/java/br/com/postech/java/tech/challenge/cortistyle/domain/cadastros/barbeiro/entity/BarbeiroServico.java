package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.barbeiro.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.servico.entity.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_barbeiro_servico")
@SuppressWarnings("serial")
public class BarbeiroServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long barbeiroId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servico", referencedColumnName = "id")
    private Servico servico;
}
