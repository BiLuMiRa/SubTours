package com.subtours.model;

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
    @Column(name = "id_caverna")
    private Long idCaverna;

    @Column(name = "nome_caverna", nullable = false)
    private String nomeCaverna;

    @Column(name = "cod_cadAmbiental", nullable = false, unique = true, length = 6)
    private String codCadAmbiental;

    @Column(name = "municipio", nullable = false, length = 25)
    private String municipio;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "altitude", length = 4)
    private BigDecimal altitude;

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

    public boolean addSetor(SetorPesquisa sp){
        if(this.setores != null && sp != null){
            this.setores.add(sp);
            sp.setCaverna(this);
            return true;
        }
        return false;
    }
}