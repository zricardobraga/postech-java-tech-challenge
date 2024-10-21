package br.com.postech.java.tech.challenge.cortistyle.domain.cadastros.filial.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.login.usuario.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_filial_barbeiro")
@SuppressWarnings("serial")
public class BarbeiroFilial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "barbeiro", referencedColumnName = "id")
    private Usuario barbeiro;

}
