package com.subtours.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.subtours.enums.situacaoValidacaoColetaEnum;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity 
@Table(name = "coleta_cientifica")
public class ColetaCientifica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coletaCientifica")
    private Integer id;

    // como a classe ainda não está pronta, vou deixar comentado
    // @Column(name = "setor_id", nullable = false, length = 30)
    // private Setor setor;
    @ManyToOne 
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

    @Column(name = "profundidade")
    private BigDecimal profundidade;

    @Column(name = "observacoes", length = 150)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_validacao")
    private situacaoValidacaoColetaEnum situacaoValidacao;

    @OneToMany(mappedBy = "coleta")
    private List<AmostraCientifica> amostras = new ArrayList<>();

    @ManyToOne 
    @JoinColumn(name = "expedicao_id", 
        foreignKey = @ForeignKey(name = "FK_coleta_expedicao"))
    private Expedicao expedicao;
}
