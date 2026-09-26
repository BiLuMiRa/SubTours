package com.subtours.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import com.subtours.enums.estadoEquipamento;

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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "utilizacao_equipamento")
public class UtilizacaoEquipamento {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utiliza_equipamento")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false, 
        foreignKey = @ForeignKey(name = "fk_responsavel"))
    private Pessoa responsavel;

    @ManyToOne
    @JoinColumn(name = "equipamento_expedicao_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_usa_equipamento_expedicao"))
    private Equipamento equipamento;

    @ManyToOne 
    @JoinColumn(name = "expedicao_equipamento_id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_expedicao_usa_equipamento"))
    private Expedicao expedicao;

    @Column(name = "data_hora_retirada", nullable = false)
    private LocalDateTime dataHoraRetirada;

    @Column(name = "previsao_devolucao", nullable = false)
    private LocalDate previsaoDevolucao;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_saida", nullable = false)
    private estadoEquipamento estadoSaida;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_retorno")
    private estadoEquipamento estadoRetorno;

    @Column(name = "custo_avaria")
    private BigDecimal custoAvaria;
}
