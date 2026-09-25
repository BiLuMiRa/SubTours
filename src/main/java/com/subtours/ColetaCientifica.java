package com.subtours;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity 
@Table(name = "ColetaCientifica")
public class ColetaCientifica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coletaCientifica")
    private Integer id;

    @Column(name = "setor", nullable = false, length = 30)
    private String setor;

    @Column(name = "pessquisador_id", nullable = false)
    private Pesquisador pesquisador;

    @Column(name = "metodoEmpregado", nullable = false)
    private String metodoEmpregado;

    @Column(name = "descricaoPonto", nullable = false)
    private String descricaoPonto;

    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Column(name = "umidadeRelativa", nullable = false)
    private Double umidadeRelativa;

    @Column(name = "observacoes", length = 100)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacaoValidacao")
    private String situacaoValidacao;
}
