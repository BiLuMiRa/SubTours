package com.subtours;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "amostra")
public class Amostra {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amostra")
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "massa")
    private Double massa;

    @Column(name = "volume")
    private Double volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidadeMedida", nullable = false)
    private String unidadeMedida;

    @Column(name = "dataAcondicionamento", nullable = false)
    private LocalDate dataAcondicionamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicaoConservacao")
    private String condicaoConservacao;

    @Column(name = "materialPerigoso")
    private String materialPerigoso;

    @Column(name = "fotografia", columnDefinition = "bytea")
    private byte[] fotografiaBinaria;

    @Column(name = "observacoes", length = 200)
    private String obsorvacoes;
    
}
