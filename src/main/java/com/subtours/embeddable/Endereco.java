package com.subtours.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable 
public class Endereco {
    @Column(name = "logradouro", nullable = false, length = 20)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private short numero;

    @Column(name = "complemento", length = 30)
    private String complemento;

    @Column(name = "bairro", nullable = false, length = 30)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "unidade_fed", nullable = false, length = 2)
    private String unidadeFed;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;
}
