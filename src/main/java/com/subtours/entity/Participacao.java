package com.subtours.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.subtours.enums.papelExpedicaoEnum;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "participacao",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_participacao_pessoa_expedicap",
        columnNames = {"cod_pessoa", "cod_exped"}
        )
    }
) 
public class Participacao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_participacao")
    private short codParticipacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel_expedicap", nullable = false)
    private papelExpedicaoEnum papelExpedicao;

    @Column(name = "data_confirmacao", nullable = false)
    private LocalDate dataConfirmacao;

    @Column(name = "valor_diaria", nullable = false)
    private BigDecimal valorDiaria;

    @Column(name = "quantidade_dias", nullable = false)
    private short quantidadeDias;

    @Column(name = "presenca", nullable = false)
    private Boolean presenca;

    @Column(name = "observacoes")
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_pessoa", nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_participacao_pessoa"
        )
    )
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_exped", nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_participacao_expedicao"
        )
    )
    private Expedicao expedicao;

}
