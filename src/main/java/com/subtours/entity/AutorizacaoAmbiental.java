package com.subtours.entity;

import com.subtours.enums.situacaoAutorizacaoAmbientalEnum;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity 
@Table(name = "autorizacao_ambiental")
public class AutorizacaoAmbiental {
    @Id 
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "num_autoriz")
    private short numAutoriz;

    @Column(name = "orgao_emissor", nullable = false, length = 30)
    private String orgaoEmissor;

    @Column(name = "dt_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "validade", nullable = false)
    private LocalDate validade;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private situacaoAutorizacaoAmbientalEnum situacao;

    @Column(name = "obs", length = 300)
    private String observacoes;

    @Column(name = "pdf_ass", columnDefinition = "bytea", nullable = false)
    private byte[] pdfAssinado;
}