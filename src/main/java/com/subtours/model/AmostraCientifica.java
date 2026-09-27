package com.subtours.model;

import java.time.LocalDate;

import com.subtours.enums.categoriaAmostraEnum;
import com.subtours.enums.condicaoAmostraEnum;
import com.subtours.enums.unidadeMedidaAmostraEnum;

import jakarta.persistence.Basic;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@Table(name = "amostra_cientifica")
public class AmostraCientifica {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amostra")
    private Integer id;

    @Column(name = "cod_amostra", unique = true, nullable = false, length = 6)
    private String codAmostra;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private categoriaAmostraEnum categoria;

    @Column(name = "massa")
    private Double massa;

    @Column(name = "volume")
    private Double volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    private unidadeMedidaAmostraEnum unidadeMedida;

    @Column(name = "data_acondicionamento", nullable = false)
    private LocalDate dataAcondicionamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_conservacao")
    private condicaoAmostraEnum condicaoConservacao;

    @Column(name = "material_perigoso")
    private String materialPerigoso;

    // @Lob 
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fotografia", columnDefinition = "bytea")
    private byte[] fotografiaBinaria;

    @Column(name = "observacoes", length = 100)
    private String obsorvacoes;

    @ManyToOne
    @JoinColumn(name = "coleta_id", 
        foreignKey = @ForeignKey(name = "FK_coleta_amostra"))
    private ColetaCientifica coleta;
    
}
