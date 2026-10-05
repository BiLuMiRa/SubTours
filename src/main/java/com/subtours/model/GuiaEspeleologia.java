package com.subtours.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor 
@Setter 
@Getter 
@Entity 
@AllArgsConstructor 
@SuperBuilder 
@Table(name = "pessoa_guia_espeleologia")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class GuiaEspeleologia extends Pessoa{
    @Column(name = "numCredenc", nullable = false)
    private String num_credenc;

    @Column(name = "nivelCertif")
    private Integer nivel_certif;

    @Column(name = "validadeCertif")
    private LocalDate validade_certif;

    @Column(name = "qntdExpedicoes")
    private Integer qntd_expedicoes;
}
