package com.subtours.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.subtours.enums.situacaoValidacaoColetaEnum;

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
@Table(name = "coleta_cientifica")
public class ColetaCientifica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coletaCientifica")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_setor_coleta")
    )
    private SetorPesquisa setor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pesquisador_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_pesquisador")
    )
    private Pesquisador pesquisador;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "metodoEmpregado", nullable = false, length = 50)
    private String metodoEmpregado;

    @Column(name = "descricaoPonto", nullable = false)
    private String descricaoPonto;

    @Column(name = "temperatura", nullable = false)
    private BigDecimal temperatura;

    @Column(name = "umidadeRelativa", nullable = false)
    private BigDecimal umidadeRelativa;

    @Column(name = "profundidade", nullable = false)
    private BigDecimal profundidade;

    @Column(name = "observacoes", length = 150)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_validacao")
    private situacaoValidacaoColetaEnum situacaoValidacao;

    @OneToMany(mappedBy = "coleta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AmostraCientifica> amostras = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expedicao_id", 
        foreignKey = @ForeignKey(name = "FK_coleta_expedicao"))
    private Expedicao expedicao;

    public boolean addAmostra(AmostraCientifica a){
        if(this.amostras != null && a != null){
            this.amostras.add(a);
            a.setColeta(this);
            return true;
        }
        return false;
    }
}
