package com.subtours.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.subtours.enums.situacaoExpedicaoEnum;
import com.subtours.entity.Caverna;
import com.subtours.entity.Relatorio;
import com.subtours.entity.PlanoSeguranca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity
@Table(name = "expedicao")
public class Expedicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codExpedicao")
    private short id;

    @Column(name = "titulo", nullable = false, length = 30)
    private String titulo;

    @Column(name = "objetivo", nullable = false, length = 50)
    private String objetivo;

    @Column(name = "inicio", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "termino", nullable = false)
    private LocalDateTime termino;

    @Column(name = "orcamento", nullable = false)
    private BigInteger orcamento;

    @Column(name = "custo", nullable = false)
    private BigInteger custo;

    @Column(name = "qntdParticip", nullable = false)
    private short qntdParticip;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private situacaoExpedicaoEnum situacao;

    @Column(name = "cancelEmerg", nullable = false)
    private boolean cancelEmerg;

    @ManyToOne 
    @JoinColumn(name = "codcarvena", foreignKey = @ForeignKey(name = "fk_caverna"))
    @Column(name = "caverna", nullable = false)
    private Caverna caverna;

    @OneToOne 
    @JoinColumn(name = "codPlanSeg")
    @Column(name = "planoSeguranca", nullable = false)
    private PlanoSeguranca planoSeguranca;

    @OneToOne 
    @JoinColumn(name = "codAutoriz")
    @Column(name = "autorizAmbiental", nullable = false)
    private AutorizacaoAmbiental autorizAmbiental;

    @OneToOne 
    @JoinColumn(name = "codRelatorio")
    @Column(name = "relatorio", nullable = false)
    private Relatorio relatorio;
}
