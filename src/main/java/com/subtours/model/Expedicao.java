package com.subtours.model;

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
@Table(name = "expedicao")
public class Expedicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expedicao")
    private Long idExped;

    @Column(name = "cod_exped", unique = true, nullable = false, length = 14)
    private String codExped;

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
    private boolean cancelEmerg = false;

    @ManyToOne
    @JoinColumn(name = "carvena_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_caverna_expedicao"))
    private Caverna caverna;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "plan_seg_id", foreignKey = @ForeignKey(name = "fk_plano_expedicao"))
    private PlanoSeguranca planoSeguranca;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "autoriz_id", foreignKey = @ForeignKey(name = "fk_autorizacao_expedicao"))
    private AutorizacaoAmbiental autorizAmbiental;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "relat_id", foreignKey = @ForeignKey(name = "fk_relatorio_expedicao"))
    private Relatorio relatorio;

    @OneToMany(mappedBy = "expedicao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Participacao> participacoes = new ArrayList<>();

    public boolean addParticipacao(Participacao p){
        if(this.participacoes != null && p != null){
            this.participacoes.add(p);
            p.setExpedicao(this);
            return true;
        }
        return false;
    }

    @OneToMany(mappedBy = "expedicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColetaCientifica> coletasCientificas = new ArrayList<>();

    public boolean addColeta(ColetaCientifica cc){
        if(this.coletasCientificas != null && cc != null){
            this.coletasCientificas.add(cc);
            cc.setExpedicao(this);
            return true;
        }
        return false;
    }

    @OneToMany(mappedBy = "expedicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UtilizacaoEquipamento> utilizacoes = new ArrayList<>();

    public boolean addUtilizacao(UtilizacaoEquipamento ue){
        if(this.utilizacoes != null && ue != null){
            this.utilizacoes.add(ue);
            ue.setExpedicao(this);
            return true;
        }
        return false;
    }
}
