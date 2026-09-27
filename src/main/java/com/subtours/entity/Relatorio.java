package com.subtours.entity;

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
    @Column(name = "id_relatorio")
    private Integer id;

    @Column(name = "titulo", length = 70, nullable = false)
    private String titulo;

    @Column(name = "resumo", length = 250)
    private String Resumo;

    @Column(name = "data_submissao")
    private LocalDate dataSubmissao;

    @Column(name = "numero_paginas")
    private Integer numeroPaginas;

    @Column(name = "arquivo_completo", columnDefinition = "bytea")
    private byte[] arqCompleto;

    @Column(name = "publicacao_aprovada", columnDefinition = "char(1)")
    @Convert(converter = TrueFalseConverter.class)
    private Boolean publicacaoAprovada;

    @OneToOne(mappedBy = "relatorio")
    @JoinColumn(name = "expedicao_id", nullable = false, 
        foreignKey = @ForeignKey(name = "fk_expedicao")
    )
    private Expedicao expedicao;
}
