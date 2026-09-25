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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "utiliza_equipamento")
public class UtilizaEquipamento {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utiliza_equipamento")
    private Integer id;

    @Column(name = "usuario_equipamento_id", nullable = false)
    private Pessoa usuarioEquipamento;

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
