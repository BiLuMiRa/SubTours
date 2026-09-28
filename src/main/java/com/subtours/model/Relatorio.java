package com.subtours.model;

import java.time.LocalDate;

import org.hibernate.type.TrueFalseConverter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Entity 
@Table(name = "relatorio")
public class Relatorio {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rel")
    private Long id;

    @Column(name = "titulo", length = 70, nullable = false)
    private String titulo;

    @Column(name = "resumo", length = 250)
    private String Resumo;

    @Column(name = "data_submissao")
    private LocalDate dataSubmissao;

    @Column(name = "num_paginas")
    private Integer numeroPaginas;

    // @Lob 
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arq_completo", columnDefinition = "bytea")
    private byte[] arqCompleto;

    @Column(name = "publicacao_aprovada")
    private Boolean publicacaoAprovada;

    @OneToOne(mappedBy = "relatorio")
    // @JoinColumn(name = "expedicao_id", nullable = false, 
    //     foreignKey = @ForeignKey(name = "fk_expedicao")
    // )
    private Expedicao expedicao;
}
