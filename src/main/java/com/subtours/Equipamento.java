package com.subtours;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity
@Table(name = "Equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamento")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 70)
    private String nome;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private String tipo;

    @Column(name = "fabricante", nullable = false, length = 50)
    private String fabricante;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "dataCompra", nullable = false)
    private LocalDate dataCompra;

    @Column (name = "situacaoOperacional", nullable = false)
    @Enumerated(EnumType.STRING)
    private String situacaoOperacional;
   
    @Column(name = "indicacaoCalibracao", nullable = false)
    private Double indicacaoCalibracao;
    
    @Column(name = "dataUltimaManuntencao")
    private LocalDate dataUltimaManutencao;
}
