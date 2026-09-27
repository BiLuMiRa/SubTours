package com.subtours.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.subtours.embeddable.Localizacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "caverna")
public class Caverna {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "num_caverna")
    private Integer numCaverna;

    @Column(name = "nome_caverna", nullable = false)
    private String nomeCaverna;

    @Column(name = "cod_cadAmbiental", nullable = false, unique = true)
    private String codCadAmbiental;

    @Column(name = "municipio", nullable = false)
    private String municipio;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "ultima_insp", nullable = false)
    private LocalDate ultimaInsp;

    @Column(name = "ind_acesso", nullable = false)
    private Boolean indAcesso = true;

    @Column(name = "extensao")
    private BigDecimal extensao;

    @Embedded 
    private Localizacao localizacao;

    @OneToMany(mappedBy = "caverna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetorPesquisa> setores = new ArrayList<>();
}