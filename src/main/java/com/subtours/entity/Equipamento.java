package com.subtours.entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.TrueFalseConverter;

import com.subtours.enums.situacaoOperacionalEquipamentoEnum;
import com.subtours.enums.tipoEquipamentoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamento")
    private Integer id;

    @Column(name = "cod_patrimonial", unique = true, nullable = false)
    private Integer codPatrimonial;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private tipoEquipamentoEnum tipo;

    @Column(name = "fabricante", length = 50)
    private String fabricante;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column (name = "situacao_operacional", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private situacaoOperacionalEquipamentoEnum situacaoOperacional;
   
    @Column(name = "exige_calibracao", nullable = false, columnDefinition = "char(1)")
    @Convert(converter = TrueFalseConverter.class)
    private Boolean exigeCalibracao;
    
    @Column(name = "data_ultima_manuntencao")
    private LocalDate dataUltimaManutencao;

    @OneToMany(mappedBy = "equipamento")
    private List<UtilizacaoEquipamento> utilizacoes = new ArrayList<>();
}
