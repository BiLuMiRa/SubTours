package com.subtours.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.subtours.enums.condicaoCorrenteEnum;
import com.subtours.enums.nivelDificuldadeEnum;

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
@Table(name = "setor_pesquisa")
public class SetorPesquisa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_setor")
    private Integer numSetor;

    @Column(name = "denominacao", nullable = false)
    private String denominacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_dificuldade", nullable = false)
    private nivelDificuldadeEnum dificuldade;

    @Column(name = "profuncidade_maxima", nullable = false)
    private BigDecimal profuncidadeMaxima;

    @Column(name = "extensao", nullable = false)
    private BigDecimal extensao;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "risco_inundacao", nullable = false)
    private nivelDificuldadeEnum riscoInundacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao_corrente", nullable = false)
    private condicaoCorrenteEnum condicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caverna", nullable = false,
        foreignKey = @ForeignKey(name = "fk_caverna"))
    private Caverna caverna;

    @OneToMany(mappedBy = "setor")
    private List<ColetaCientifica> coletas = new ArrayList<>();

    public boolean addColeta(ColetaCientifica cc){
        if(this.coletas != null && cc != null){
            this.coletas.add(cc);
            cc.setSetor(this);
            return true;
        }
        return false;
    }
}
