package com.subtours.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.subtours.enums.situacaoExpedicaoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Column(name = "objetivo", nullable = false, length = 50)
    private String objetivo;

    @Column(name = "inicio", nullable = false)
    private LocalDateTime inicio;

    @Column(name = "termino", nullable = false)
    private LocalDateTime termino;

    @Column(name = "orcamento", nullable = false, precision = 15, scale = 2)
    private BigDecimal orcamento;

    @Column(name = "custo", nullable = false, precision = 15, scale = 2)
    private BigDecimal custo;

    @Column(name = "qntd_particps", nullable = false)
    private Integer qntdParticip;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private situacaoExpedicaoEnum situacao;

    @Column(name = "canclmnt_emerg", nullable = false)
    private boolean cancelEmerg;

    // como a classe ainda não está pronta, vou deixar comentado
    // @ManyToOne(fetch = FetchType.LAZY, cascade = )
    // @JoinColumn(name = "cod_carvena", foreignKey = @ForeignKey(name = "fk_caverna"))
    // private Caverna caverna;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cod_plan_seg")
    private PlanoSeguranca planoSeguranca;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cod_autoriz")
    private AutorizacaoAmbiental autorizAmbiental;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cod_rel")
    private Relatorio relatorio;

    @OneToMany(mappedBy = "expedicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColetaCientifica> coletasCientificas = new ArrayList<>();

    @OneToMany(mappedBy = "expedicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UtilizacaoEquipamento> utilizacoes = new ArrayList<>();
}
