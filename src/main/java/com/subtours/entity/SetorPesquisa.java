package com.subtours.entity;

import java.math.BigDecimal;

import javax.annotation.processing.Generated;

import com.subtours.enums.condicaoAmostraEnum;
import com.subtours.enums.nivelDificuldadeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity
@Table(name = "setor_pesquisa")
public class SetorPesquisa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_setor")
    private short numSetor;

    @Column(name = "denominacao")
    private String denominacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_dificuldade")
    private nivelDificuldadeEnum dificuldade;

    @Column(name = "profuncidade_maxima")
    private BigDecimal profuncidadeMaxima;

    @Column(name = "extensao")
    private BigDecimal extensao;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco_inundacao")
    private nivelDificuldadeEnum riscoInundacao;

    @Column(name = "condicao_corrente")
    private condicaoAmostraEnum condicao;

}
