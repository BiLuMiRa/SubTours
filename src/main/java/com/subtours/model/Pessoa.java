package com.subtours.model;

import com.subtours.embeddable.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", nullable = false)
    private Long id;

    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "dt_nasc", nullable = false)
    private LocalDate datanasc;
    
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "fone", nullable = false, length = 13)
    private String telefone;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Embedded 
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa")
    private List<Participacao> participacoes = new ArrayList<>();

    public boolean addParticipacao(Participacao p){
        if(this.participacoes != null && p != null){
            this.participacoes.add(p);
            p.setPessoa(this);
            return true;
        }
        return false;
    }
    
    @OneToMany(mappedBy = "responsavel")
    private List<UtilizacaoEquipamento> retiradas = new ArrayList<>();

    public boolean addRetirada(UtilizacaoEquipamento ue){
        if(this.retiradas != null && ue != null){
            this.retiradas.add(ue);
            ue.setResponsavel(this);
            return true;
        }
        return false;
    }

}
