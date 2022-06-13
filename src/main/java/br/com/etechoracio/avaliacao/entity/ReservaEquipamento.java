package br.com.etechoracio.avaliacao.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.etechoracio.avaliacao.enums.StatusReservaEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "TBL_PASSAGEIRO")
public class ReservaEquipamento {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NR_ESTACAO")
    private Integer numero;

    @Column(name = "TX_CPF")
    private String cpf;

    @Column(name = "TX_NOME")
    private String nome;

    @Column(name = "TX_EMAIL")
    private String email;

    @Column(name = "DT_RESERVA")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "TX_STATUS")
    private StatusReservaEnum status;

}
