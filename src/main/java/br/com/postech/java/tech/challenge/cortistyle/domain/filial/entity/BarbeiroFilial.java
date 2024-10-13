package br.com.postech.java.tech.challenge.cortistyle.domain.filial.entity;

import br.com.postech.java.tech.challenge.cortistyle.domain.usuario.entity.Usuario;
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
    private Long filialId;

    @ManyToOne
    @JoinColumn(name = "barbeiro", referencedColumnName = "id")
    private Usuario barbeiro;

}
