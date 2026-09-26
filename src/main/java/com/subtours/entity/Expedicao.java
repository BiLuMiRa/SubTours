package com.subtours.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.subtours.enums.situacaoExpedicaoEnum;

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
import jakarta.persistence.OneToMany;
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
    @Column(name = "cod_exped")
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

    @Column(name = "qntd_particps", nullable = false)
    private short qntdParticip;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private situacaoExpedicaoEnum situacao;

    @Column(name = "canclmnt_emerg", nullable = false)
    private boolean cancelEmerg;

    @ManyToOne 
    @JoinColumn(name = "cod_carvena", foreignKey = @ForeignKey(name = "fk_caverna"))
    @Column(name = "caverna", nullable = false)
    private Caverna caverna;

    @OneToOne 
    @JoinColumn(name = "cod_plan_seg")
    @Column(name = "plano_seg", nullable = false)
    private PlanoSeguranca planoSeguranca;

    @OneToOne 
    @JoinColumn(name = "cod_autoriz")
    @Column(name = "autorizAmbiental", nullable = false)
    private AutorizacaoAmbiental autorizAmbiental;

    @OneToOne 
    @JoinColumn(name = "cod_rel")
    @Column(name = "relatorio", nullable = false)
    private Relatorio relatorio;

    @OneToMany(mappedBy = "expedicao")
    private List<ColetaCientifica> coletasCientificas = new ArrayList<>();

    @OneToMany(mappedBy = "expedicao")
    private List<UtilizacaoEquipamento> utilizacoes = new ArrayList<>();
}
