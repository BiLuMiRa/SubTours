package com.subtours;

import jakarta.persistence.*;

@Entity 
public class ColetaCientifica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String setor;

    @Column(nullable = false)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String metodoEmpregado;

    @Column(nullable = false)
    private String descricaoPonto;

    @Column(nullable = false)
    private Integer temperatura;

    @Column(nullable = false)
    private Integer umidadeRelativa;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    private String situacaoValidacao;
}
