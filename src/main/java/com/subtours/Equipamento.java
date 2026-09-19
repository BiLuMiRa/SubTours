package com.subtours;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 70)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String tipo;

    @Column(nullable = false, length = 50)
    private String fabricante;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private LocalDate dataCompra;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private String situacaoOperacional;
   
    @Column(nullable = false)
    private Double indicacaoCalibracao;
    
    private LocalDate dataUltimaManutencao;
}
