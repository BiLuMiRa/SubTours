package com.subtours.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
    
    @Column(name = "registro_inst", nullable = false, length = 4)
    private short registroInst;

    @Column(name = "area_pesq", nullable = false, length = 30)
    private String areaPesquisa;

    @Column(name = "titulacao", nullable = false, length = 30)
    private String titulacao;

    @Column(name = "valorDiarioBolsa", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor_diario_bolsa;

    @OneToMany(mappedBy = "pesquisador")
    private List<ColetaCientifica>coletas = new ArrayList<>();

    public boolean addColeta(ColetaCientifica cc){
        if(this.coletas != null && cc != null){
            this.coletas.add(cc);
            cc.setPesquisador(this);
            return true;
        }
        return false;
    }
}
