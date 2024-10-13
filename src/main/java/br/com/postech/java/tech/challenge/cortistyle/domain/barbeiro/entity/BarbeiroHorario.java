package br.com.postech.java.tech.challenge.cortistyle.domain.barbeiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_barbeiro_horario")
@SuppressWarnings("serial")
public class BarbeiroHorario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long barbeiroId;
    private String horario;
    private Boolean agendado;

}
