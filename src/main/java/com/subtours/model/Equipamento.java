package com.subtours.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.TrueFalseConverter;

import com.subtours.enums.situacaoOperacionalEquipamentoEnum;
import com.subtours.enums.tipoEquipamentoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamento")
    private Long idEquipamento;

    @Column(name = "cod_patrimonial", unique = true, nullable = false, length = 6)
    private String codPatrimonial;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private tipoEquipamentoEnum tipo;

    @Column(name = "fabricante", length = 50)
    private String fabricante;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column (name = "situ_operacional", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private situacaoOperacionalEquipamentoEnum situacaoOperacional;
   
    @Column(name = "exige_calibracao", nullable = false)
    private Boolean exigeCalibracao;
    
    @Column(name = "data_ultima_manuntencao")
    private LocalDate dataUltimaManutencao;

    @OneToMany(mappedBy = "equipamento", fetch = FetchType.LAZY)
    private List<UtilizacaoEquipamento> utilizacoes = new ArrayList<>();

    public boolean addUso(UtilizacaoEquipamento ue){
        if( this.utilizacoes != null && ue != null){
            this.utilizacoes.add(ue);
            ue.setEquipamento(this);
            return true;
        }
        return false;
    }
}
