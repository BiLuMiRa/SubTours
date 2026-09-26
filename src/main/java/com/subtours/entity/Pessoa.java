package com.subtours.entity;

import com.subtours.embeddable.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor 
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
    
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fone", nullable = false, length = 13)
    private String telefone;

    //não tenho certeza desse tipo
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Embedded 
    private Endereco endereco;

    @OneToMany(mappedBy = "responsavel")
    private List<UtilizacaoEquipamento> retiradas = new ArrayList<>();

}
