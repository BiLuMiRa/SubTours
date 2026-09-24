package com.subtours.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Setter 
@Getter 
@Entity 
@Table(name = "pessoa_guia_espeleologia")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class GuiaEspeleologia extends Pessoa{
    @Column(name = "numCredenc", nullable = false)
    private long num_credenc;

    @Column(name = "nivelCertif")
    private short nivel_certif;

    @Column(name = "validadeCertif")
    private LocalDate validade_certif;

    @Column(name = "qntdExpedicoes")
    private short qntd_expedicoes;
}
