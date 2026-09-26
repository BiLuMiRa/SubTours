package com.subtours.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@SuperBuilder 
@Entity 
@Table(name = "pessoa_pesquisador")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Pesquisador extends Pessoa{
    
    @Column(name = "registro_inst", nullable = false)
    private Integer registroInst;

    @Column(name = "area_pesq", nullable = false, length = 30)
    private String areaPesquisa;

    @Column(name = "titulacao", nullable = false, length = 30)
    private String titulacao;

    @Column(name = "valorDiarioBolsa", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor_diario_bolsa;
}
