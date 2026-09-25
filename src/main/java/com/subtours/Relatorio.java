package com.subtours;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity 
@Table(name = "relatorio")
public class Relatorio {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relatorio")
    private Integer id;

    @Column(name = "titulo", length = 70)
    private String titulo;

    @Column(name = "resumo")
    private String Resumo;

    @Column(name = "dataSubmissao")
    private LocalDate dataSubmissao;

    @Column(name = "numeroPaginas")
    private Integer numeroPaginas;

    @Column(name = "arquivoCompleto", columnDefinition = "bytea")
    private byte[] arqCompleto;

    @Column(name = "publicacaoAprovada")
    private Boolean publicacaoAprovada;

}
