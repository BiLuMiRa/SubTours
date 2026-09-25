package com.subtours.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity 
@Table (name = "plano_seg")
public class PlanoSeguranca {
    @Id 
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "cod_plan_seg")
    private short id;

    @Column(name = "proceds_evac", nullable = false, length = 300)
    private String procedsEvacuacao;

    @Column(name = "ponto_enctr", nullable = false, length = 50)
    private String pontoEncontro;

    @Column(name = "tempo_sem_comunic", nullable = false)
    private LocalTime tempoSemComunic;

    @Column(name = "fone_emerg", nullable = false, length = 11)
    private String telefoneEmerg;

    @Column(name = "precisa_med")
    private boolean precisaMedico;

    @Column(name = "mapa", columnDefinition = "bytea")
    private byte[] mapa;
}
